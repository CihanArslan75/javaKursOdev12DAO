package com.cihan.odev12.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//https://codereview.stackexchange.com/questions/126621/simple-singleton-database-connection-pool
public class ConnectionManagerPool {
	public static ConnectionManagerPool instance=null;
	private final static int MAX_CONNECTIONS = 3;
	private static Connection[] conn = new Connection[MAX_CONNECTIONS];
	private static String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static String userName = "postgres";
	private static String password = "root";
	public static int counter;
	public static boolean[] used= new boolean[MAX_CONNECTIONS];
	
		private ConnectionManagerPool() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				conn[i] = DriverManager.getConnection(url, userName, password);
				used[i] =true;
			}
			counter=0;
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection connection=null;
		int sayi=0;
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			if(used[i]==false) sayi++;
			}
		if(sayi==MAX_CONNECTIONS)
		{ 
			try 
			{
				getInstance();
				getConnection();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else 
		{
			if(counter == MAX_CONNECTIONS ) 
			{  
				counter=0;
				connection=conn[counter];
				counter++;
				
				return connection;
				
			}
			
		}
		connection=conn[counter];
	    counter++;
		if(counter==MAX_CONNECTIONS) counter=0;
		return connection;
	}
	
	public static ConnectionManagerPool getInstance() throws SQLException{
		if(instance==null) {
			instance=new ConnectionManagerPool();
		}else if(instance.getConnection().isClosed()) {
			instance=new ConnectionManagerPool()	;
		}
		return instance;
	}
	
		
}

	
	
