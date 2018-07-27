package src.main.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import src.main.java.model.Stats;

public class StatsDAO {

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
	
	public static void insertOrIncrementStats(Long urlId, Stats stats) {
	    
	    try {
            connect();
            String sql = "select 1 from stats where id_url = ? and date = ? and ip_address = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, urlId);
            ps.setObject(2, stats.getDate());
            ps.setString(3, stats.getIpAddress());
            ResultSet rs = ps.executeQuery();
            
            // Row exists so update
            if (rs.next()) {
                
                String sqlStats = "update stats set nb_click = nb_click + 1 where id_url = ? and date = ? and ip_address = ?";
                PreparedStatement psStats = conn.prepareStatement(sqlStats);           
                psStats.setLong(1, urlId);
                psStats.setObject(2, stats.getDate());
                psStats.setString(3, stats.getIpAddress());
                psStats.executeUpdate();
                psStats.close();
            } else {
                
                String sqlStats = "INSERT INTO stats (id, id_url, date, ip_address, nb_click)" + "VALUES (default, ?, ?, ?, 1)";
                PreparedStatement psStats = conn.prepareStatement(sqlStats);   
                psStats.setLong(1, urlId);
                psStats.setObject(2, stats.getDate());
                psStats.setString(3, stats.getIpAddress());
                psStats.executeUpdate();
                psStats.close();
            }
            
            rs.close();
            ps.close();
            close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static Map<String, Integer> findDateAndSumNbClickByUrlId(Long urlId) {
	    
	    Map<String, Integer> dataMap = new LinkedHashMap<>();
	    
	    try {
            connect();
            String sql = "SELECT date, SUM(nb_click) FROM stats WHERE id_url = ? GROUP BY date ORDER BY date";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, urlId);
            ResultSet rs = ps.executeQuery();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            
            while (rs.next()) {
                dataMap.put(rs.getObject(1, LocalDate.class).format(formatter), rs.getInt(2));
            }
     
            rs.close();
            ps.close();
            close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    
	    return dataMap;
	    
	}

}
