package com.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	Connection connection;
	Statement statement;
	
	/**
	 * This method used for connected to the MySQL database
	 * @param url
	 * @param username
	 * @param password
	 */
	
	public void getDBConnection(String url, String username, String password) {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(url, username, password);
			if(connection != null) {
				System.out.println("Database is connected :");
			}else {
				System.out.println("Database is not connected :");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	/**
	 * This method used for fetch the data into the database
	 * @param query
	 * @return 
	 */
	public ResultSet getDataFromDatabase(String query) {
		ResultSet result =null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * This method returns if the query executed successfully it returns 1 or else 0.
	 * @param query
	 * @return int
	 */
	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * This method is used for close the connection of database 
	 */
	public void closeDBconnection() {
		try {
			connection.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	

}
