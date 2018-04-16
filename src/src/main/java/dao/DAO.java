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
			String sqlUrl = "INSERT INTO url (id, source_url, short_url, url_type)" + "VALUES (default, ?, ?, ?)";
			PreparedStatement psUrl = conn.prepareStatement(sqlUrl, Statement.RETURN_GENERATED_KEYS);			
			psUrl.setString(1, url.getSourceUrl());
			psUrl.setString(2, url.getShortUrl());
			psUrl.setString(3, url.getClass().getSimpleName());
			psUrl.executeUpdate();					
			
			if (!url.getPassword().equals("")) {			
				
				// Get last generated url id
				ResultSet rs = psUrl.getGeneratedKeys();
				Long urlId = null;
				if (rs.next()) urlId = rs.getLong(1);				
				String hashedPassword = BCrypt.hashpw(url.getPassword(), BCrypt.gensalt());				
				String sqlPwd = "INSERT INTO url_passwords (id, id_url, password)" + "VALUES (default, ?, ?)";
				PreparedStatement psPwd = conn.prepareStatement(sqlPwd);					
				psPwd.setLong(1, urlId);				
				psPwd.setString(2, hashedPassword);
				psPwd.executeUpdate();
				psPwd.close();
				rs.close();
			}
			
			psUrl.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadUsedUrls () {
		try {
			connect();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT short_url FROM url");
			HashSet<String> usedUrls = new HashSet<>();
			while (rs.next()) {
				usedUrls.add(rs.getString(1));
			}
			UrlGenerator.setUsedUrls(usedUrls);
			rs.close();
			statement.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
