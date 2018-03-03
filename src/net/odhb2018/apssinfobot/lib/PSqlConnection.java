package net.odhb2018.apssinfobot.lib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.odhb2018.apssinfobot.lib.khfunction.KHfunctionPSQL;

public class PSqlConnection {
	static Statement statement = null;
	static ResultSet rs = null;
	
	/*
	 * return 1; -> if the user is on faq mode
	 * return 0; -> if the user isn't on faq mode
	 * return 2; -> if the user isn't in the database
	 * return 3; -> error no connection
	*/
	public static int readDataBase(int utente) {
		try {
			
			//selector rs
			//sintassi del rs tabella
			rs=KHfunctionPSQL.dbconnection("SELECT * FROM Utenti");
			//if it's true 
			int a = esistenza(utente);
			//close connection
			a=KHfunctionPSQL.close(a);
			
			//ritorno la variabile
			return a;
			
		}catch(SQLException e) {
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return 3;
			
		}catch(ClassNotFoundException e){
			System.out.println("Impossibile connettersi: " + e.getStackTrace());
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
						System.out.println("faq mode= true");
						return 1;
					}else {
						//the user isn't on faq mode
						System.out.println("faq mode= false");
						return 0;
					}
				}else{
					//if not present
					return 2;
				}
			}
			//if not while -> no connection, no control -> error
			System.out.println("Errore return3");
			return 3;
		} catch (SQLException e) {
			System.out.println("Impossibile leggere il database"+e.getErrorCode());
			//on error
			return 3;
		}
	}
	
	//scrittura su database
	public static int writeDataBase(int utente, boolean faq) {
		try {
			String sql="INSERT INTO Utenti (id,faq) VALUES ("+utente+", "+faq+")";
			KHfunctionPSQL.dbexecute(sql);
			System.out.println("faq mode on start= false");
			int a=4;
			//controllo chiusura
			a=KHfunctionPSQL.close(a);
			return a;
		}catch(SQLException e){
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return 3;
		}catch(ClassNotFoundException e) {
			System.out.println("Impossibile connettersi: ");
			e.printStackTrace();
			return 3;
		}
	}
	
	public static int changeData(int utente, boolean faq) {
		try {
			String sql="UPDATE Utenti SET faq="+faq+" WHERE id="+utente;
			KHfunctionPSQL.dbexecute(sql);
			System.out.println("faq mode change= true");
			int a=4;
			//controllo chiusura
			a=KHfunctionPSQL.close(a);
			return a;
		}catch(SQLException e){
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return 3;
		}catch(ClassNotFoundException e) {
			System.out.println("Impossibile connettersi: ");
			e.printStackTrace();
			return 3;
		}
	}
	
	public static boolean find(int utente) {
		try {
			String sql="SELECT FROM Utenti WHERE id="+utente;
			rs=null;
			rs=KHfunctionPSQL.dbconnection(sql);
			if(rs==null) {
				System.out.println("Record non presente inseriamolo");
				KHfunctionPSQL.closeVoid();
				return false;
				
			}else {
				System.out.println("Record già presente");
				KHfunctionPSQL.closeVoid();
				return true;
			}
		}catch(SQLException e){
			System.out.println("Impossibile connettersi: "+e.getErrorCode());
			return false;
		}catch(ClassNotFoundException e) {
			System.out.println("Impossibile connettersi: " + e.getMessage());
			return false;
		}
		
	}
	
	public static String readData(int id){
		try {
			String sql = "SELECT risposta FROM dialog where id="+id;
			rs=null;
			rs=KHfunctionPSQL.dbconnection(sql);
			if(rs!=null) {
				return rs.getString("risposta");
			}else {
				System.out.println("Errore di connessione");
				return null;
			}
		}catch(SQLException e) {
			e.getMessage();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	

}
