package net.odhb2018.apssinfobot.lib.khfunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KHfunctionPSQL {
	static Connection connect = null;
	static Statement statement = null;
	static ResultSet rs = null;
	
	//return of statement
	static public ResultSet dbconnection(String querry) throws ClassNotFoundException, SQLException {
		//MySql Driver
		Class.forName("org.postgresql.Driver");
		//connection
		connect = DriverManager
				.getConnection("jdbc:postgresql://ec2-54-75-227-92.eu-west-1.compute.amazonaws.com:5432/d6hq6uvna7lf2k?sslmode=require&user=vbreuzflblwjbg&password=b487fa3b2094992edb657448923162ef7ca68929e253c387e05d842ab6a7441f");
		//for sending sql
		statement= connect.createStatement();
		
		return statement.executeQuery(querry);
		
	}
	
	public static void dbexecute(String querry) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		connect = DriverManager
				.getConnection("jdbc:postgresql://ec2-54-75-227-92.eu-west-1.compute.amazonaws.com:5432/d6hq6uvna7lf2k?sslmode=require&user=vbreuzflblwjbg&password=b487fa3b2094992edb657448923162ef7ca68929e253c387e05d842ab6a7441f");
		statement=connect.createStatement();
		statement.executeQuery(querry);
	}
	
	public static int close(int a) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connect != null) {
				connect.close();
			}
			return a;
		}catch(SQLException e) {
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return 3;
		}
	}
	
	public static void closeVoid() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}catch(SQLException e) {
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
		}
	}
}
