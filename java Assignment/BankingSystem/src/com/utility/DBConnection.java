	package com.utility;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;


	public final class DBConnection {
		
		static String UserDB = "root";
		static String passDB = "";
		static String url = "jdbc:mysql://localhost:3306/banking_system";
		static String driver = "com.mysql.cj.jdbc.Driver";
		static Connection con;
		
		public static Connection dbConnect() {
			
			/* load the driver */
			
			  try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("Driver not loaded");
				e.printStackTrace();
			}
			  
			  /* step 3 : Establish the connection */
			
			  try {
				con =DriverManager.getConnection(url, UserDB, passDB);
			} catch (SQLException e) {
				System.out.println("Connection failed");
				e.printStackTrace();
			}
			  
			  return con;
			
		}
		
		public static void dbclose() {
			
			try {
				con.close();
				
			} catch (SQLException e) {
				System.out.println("Connection could not be closed");
			}
		}

		public static void main(String[]args) {
			
			DBConnection.dbConnect();
			DBConnection.dbclose();
	}
	}

