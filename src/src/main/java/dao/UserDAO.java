package src.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import src.main.java.model.AuthenticatedUrl;
import src.main.java.model.Url;
import src.main.java.model.User;
import src.main.java.util.BCrypt;

public class UserDAO {

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
	
	// Inserts Url object
	public static User insertUser(User user) {
		try {
			connect();
			String sql = "INSERT INTO user (id, id_status, username, password, mail, account_activated)" + "VALUES (default, ?, ?, ?, ?, 0)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);			
			ps.setInt(1, user.getStatus().getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			ps.setString(4, user.getMail());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			Long userId = null;
			if (rs.next()) userId = rs.getLong(1);	
			user.setId(userId);
			
			rs.close();
			ps.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static boolean activateAccount(Long userId) {
		
		int count = 0;
		
		try {
			connect();
			String sql = "UPDATE user set account_activated = 1 WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setLong(1, userId);
			count = ps.executeUpdate();
			ps.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (count > 0);
	}
	
//	public static Boolean shortUrlExists(String shortUrl) {
//		Boolean bool = null;
//	    try {
//	    	connect();
//	    	String sql = "select 1 from url where short_url = ?";
//		    PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, shortUrl);
//			ResultSet rs = ps.executeQuery();
//			bool = rs.next();
//			rs.close();
//			ps.close();
//			close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	    
//	    System.out.println("Short Url exists : " + bool);
//	    return bool;
//	}

}
