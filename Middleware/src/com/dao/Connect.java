package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class Connect {
	
	public static Connection connection=Connect.openConnection();
	public static Connection openConnection() {
			Connection connection=null;
			
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				//	System.out.println("Driver Loaded successfully");	
					connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "abcd");
				//	System.out.println("Connected");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
										
			
			return connection;
	}
		
}
