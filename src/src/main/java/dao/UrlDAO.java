package src.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import src.main.java.model.AuthenticatedUrl;
import src.main.java.model.Url;
import src.main.java.util.BCrypt;

public class UrlDAO {

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
	
	public static void insertAuthenticatedUrl(AuthenticatedUrl authenticatedUrl) {
	    
	    try {
            connect();
            String sqlUrl = "INSERT INTO url (id, id_user, source_url, short_url, mail, creation_date, start_date, end_date, captcha, url_type)" + "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psUrl = conn.prepareStatement(sqlUrl, Statement.RETURN_GENERATED_KEYS);           
            psUrl.setLong(1, authenticatedUrl.getUser().getId());
            psUrl.setString(2, authenticatedUrl.getSourceUrl());
            psUrl.setString(3, authenticatedUrl.getShortUrl());
            psUrl.setString(4, authenticatedUrl.getMail());
            psUrl.setObject(5, authenticatedUrl.getCreationDate());
            psUrl.setObject(6, authenticatedUrl.getStartDate());
            psUrl.setObject(7, authenticatedUrl.getEndDate());
            psUrl.setBoolean(8, authenticatedUrl.getCaptcha());            
            psUrl.setString(9, authenticatedUrl.getClass().getSimpleName());
            psUrl.executeUpdate();                  
            
            if (authenticatedUrl.needsPasswordCheck()) {            
                
                // Get last generated url id
                ResultSet rs = psUrl.getGeneratedKeys();
                Long urlId = null;
                if (rs.next()) urlId = rs.getLong(1);  
                
                PreparedStatement psPwd = null;
                for (String password : authenticatedUrl.getPasswordList()) {
                    
                    if (!password.equals("")) {
                        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());             
                        String sqlPwd = "INSERT INTO url_passwords (id, id_url, password)" + "VALUES (default, ?, ?)";
                        psPwd = conn.prepareStatement(sqlPwd);                    
                        psPwd.setLong(1, urlId);                
                        psPwd.setString(2, hashedPassword);
                        psPwd.executeUpdate();
                    }
                }
                
                psPwd.close();
                rs.close();
            }
            
            psUrl.close();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	// Gets Url or AuthenticatedUrl object
	public static Url getUrlWithShortUrl(String shortUrl) {		
		Url url = null;
		try {
			connect();
			String sqlUrl = "SELECT * FROM url WHERE short_url = ?";
			PreparedStatement psUrl = conn.prepareStatement(sqlUrl);
			psUrl.setString(1, shortUrl);
			ResultSet rsUrl = psUrl.executeQuery();
			
			if (rsUrl.next()) {
				if (rsUrl.getString(10).equals("Url")) {
					
					String sqlPwd = "SELECT password FROM url_passwords WHERE id_url = ?";
					PreparedStatement psPwd = conn.prepareStatement(sqlPwd);
					psPwd.setLong(1, rsUrl.getLong(1));
					ResultSet rsPwd = psPwd.executeQuery();
					String password = "";
					if (rsPwd.next()) password = rsPwd.getString(1);	
					
					url = new Url(rsUrl.getLong(1), rsUrl.getString(3), rsUrl.getString(4), password);
					
					rsPwd.close();
					psPwd.close();
				} else {
					
				    String sqlPwd = "SELECT password FROM url_passwords WHERE id_url = ?";
                    PreparedStatement psPwd = conn.prepareStatement(sqlPwd);
                    psPwd.setLong(1, rsUrl.getLong(1));
                    ResultSet rsPwd = psPwd.executeQuery();
                    
                    ArrayList<String> passwordList = new ArrayList<>();
                    
                    while (rsPwd.next()) {
                        passwordList.add(rsPwd.getString(1));
                    }
                    
                    url = new AuthenticatedUrl(rsUrl.getLong(1), rsUrl.getString(3), rsUrl.getString(4), "",
                            passwordList, null, null, rsUrl.getString(5), rsUrl.getObject(6, LocalDate.class),
                            rsUrl.getObject(7, LocalDate.class), rsUrl.getObject(8, LocalDate.class), rsUrl.getBoolean(9));
                    
				}
			}
			rsUrl.close();
			psUrl.close();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public static Boolean shortUrlExists(String shortUrl) {
		Boolean bool = null;
	    try {
	    	connect();
	    	String sql = "select 1 from url where short_url = ?";
		    PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, shortUrl);
			ResultSet rs = ps.executeQuery();
			bool = rs.next();
			rs.close();
			ps.close();
			close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    System.out.println("Short Url exists : " + bool);
	    return bool;
	}

}
