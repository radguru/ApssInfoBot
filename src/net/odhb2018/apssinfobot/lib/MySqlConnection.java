package net.odhb2018.apssinfobot.lib;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class MySqlConnection {
	static Connection connect = null;
	static Statement statement = null;
	static ResultSet rs = null;
	
	public static String loginsql="user=infobot&password=Drf439Ho0";
	
	/*
	 * return 1; -> if the user is on faq mode
	 * return 0; -> if the user isn't on faq mode
	 * return 2; -> if the user isn't in the database
	 * return 3; -> error no connection
	*/
	public static int readDataBase(int utente) {
		try {
			
			//MySql Driver
			Class.forName("com.mysql.jdbc.Driver");
			//connection
			//TODO la sintassi del collegamento
			connect = DriverManager
					.getConnection("jdbc:mysql://db4free.net/apssinfobot?"+loginsql);
			//sending SQL query
			statement = connect.createStatement();
			//selector rs
			//sintassi del rs tabella
			rs=statement.executeQuery("select * from Utenti");
			//if it's true 
			int a = esistenza(utente);
			//close connection
			a=close(a);
			
			//ritorno la variabile
			return a;
			
		}catch(SQLException e) {
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return 3;
			
		}catch(ClassNotFoundException e){
			System.out.println("Impossibile connettersi: " + e.getMessage());
			return 3;
		}
	}
	
	
	/*
	 * return 1; -> if the user is on faq mode
	 * return 0; -> if the user isn't on faq mode
	 * return 2; -> if the user isn't in the database
	 * return 3; -> error no connection
	*/
	public static int esistenza(int utente) {
		int a = 1;
		try {
			//rs.next try
			while(rs.next()&&a!=0) {
				if(rs.getInt("id") == utente) {
					if(rs.getBoolean("faq")){
						//the user is on faq mode
						return 1;
					}else {
						//the user isn't on faq mode
						return 0;
					}
				}else{
					//if not present
					return 2;
				}
			}
			//if not while -> no connection, no control -> error
			return 3;
		} catch (SQLException e) {
			System.out.println("Impossibile leggere il database"+e.getErrorCode());
			//on error
			return 3;
		}
	}
	
	//scrittura su database
	public static void writeDataBase(int utente, boolean faq) {
		try {
			//MySql Driver
			Class.forName("com.mysql.jdbc.Driver");
			//connection
			//TODO la sintassi del collegamento
			connect = DriverManager
					.getConnection("jdbc:mysql://db4free.net/apssinfobot?"+loginsql);
			//sending SQL query
			statement = connect.createStatement();
			String sql;
			sql="INSERT INTO Utenti (id,faq) VALUES ("+utente+", "+faq+")";
			statement.execute(sql);
			
			int a=4;
			//controllo chiusura
			a=close(a);
			
		}catch(SQLException e){
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
		}catch(ClassNotFoundException e) {
			System.out.println("Impossibile connettersi: " + e.getMessage());
		}
	}
	
	private static int close(int a) {
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

}
