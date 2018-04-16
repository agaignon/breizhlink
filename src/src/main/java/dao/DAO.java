package src.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import src.main.java.model.Url;
import src.main.java.util.BCrypt;
import src.main.java.util.UrlGenerator;
public class DAO {

	private static Connection conn;

	private static void connect() {
		try {
			if(System.getProperty("os.name").toLowerCase().equals("linux")) {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/breizhlink", "root", "new_password");				
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/breizhlink", "root", "");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertUrl(Url url) {
		try {
			connect();
			String sql1 = "INSERT INTO url (id, source_url, short_url, url_type)" + "VALUES (default, ?, ?, ?)";
			PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);			
			preparedStatement1.setString(1, url.getSourceUrl());
			preparedStatement1.setString(2, url.getShortUrl());
			preparedStatement1.setString(3, url.getClass().getSimpleName());
			preparedStatement1.executeUpdate();
			preparedStatement1.close();
			
			if (url.getPassword() != null) {
				
				String sql2 = "SELECT id FROM url where short_url = ?";
				PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);			
				preparedStatement2.setString(1, url.getShortUrl());
				ResultSet rs = preparedStatement2.executeQuery();
				rs.next();
				Long urlId = rs.getLong(1);
				preparedStatement2.close();
				
				String sql3 = "INSERT INTO url_passwords (id, id_url, password)" + "VALUES (default, ?, ?)";
				PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);					
				preparedStatement3.setLong(1,urlId);
				String hashedPassword = BCrypt.hashpw(url.getPassword(), BCrypt.gensalt());
				preparedStatement3.setString(2, hashedPassword);
			}
			
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadUsedUrls () {
		try {
			connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT short_url FROM url");
			HashSet<String> usedUrls = new HashSet<>();
			while (rs.next()) {
				usedUrls.add(rs.getString(1));
			}
			UrlGenerator.setUsedUrls(usedUrls);
			rs.close();
			stmt.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
