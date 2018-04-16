package src.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import src.main.java.model.Url;
import src.main.java.util.UrlGenerator;

public class DAO {

	private static Connection conn;

	private static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/breizhlink", "root", "");
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
			String sql = "INSERT INTO url (id, original_url, shortened_url, password)" + "VALUES (default, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);			
			preparedStatement.setString(1, url.getOriginalUrl());
			preparedStatement.setString(2, url.getShortenedUrl());
			preparedStatement.setString(3, url.getPassword());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadUsedUrls () {
		try {
			connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT shortened_url FROM url");
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
