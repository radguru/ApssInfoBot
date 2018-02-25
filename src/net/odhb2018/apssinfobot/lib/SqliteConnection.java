package net.odhb2018.apssinfobot.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteConnection {
	
	public static Connection conn =null;
	
	public static void connection(String db){
		
		try {
			String url = "jdbc:sqlite:sqlitedb/"+db;
			conn=DriverManager.getConnection(url);
		}catch(SQLException e){
			System.out.println("Connection failed "+e.getMessage());
		}
	}
	
	public static void close() throws SQLException {
		conn.close();
	}
	
	public static ResultSet find(String var) {
		String sql= "SELECT id, Domanda, Risposta, Link " + "FROM Faq where Domanda = ?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, var);
			rs = pstmt.executeQuery();
		}catch(SQLException e) {
			System.out.println("Connection failed "+e.getMessage());
		}
		
		return rs;
			
	}
	

}
