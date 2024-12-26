package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.testng.annotations.Test;

public class AddColDatabse {
	
	Connection connection = null;
	PreparedStatement preStmt = null;
	
	
	
	@Test
	public void addColumn() {
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		if(connection != null) {
			System.out.println("database is connected");
		}
		
		String sql = "alter table student add email varchar(30)";
		preStmt = connection.prepareStatement(sql);
		preStmt.executeUpdate();
		
		String sqlVerify = "select * from student;";
		ResultSet result = preStmt.executeQuery();
		ResultSetMetaData colnames = result.getMetaData();
		int colSize = colnames.getColumnCount();
		String addColName = "email";
		boolean flag = false;
		for(int i = 1; i <= colSize; i++) {
			String colNamesText = colnames.getColumnName(i);
			if(addColName.equals(colNamesText)) {
				flag = true;
				System.out.println(colNamesText + " is add to student table ");
			}
		}
		if(flag == false) {
			System.out.println("column is not added in student table ");
		}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		finally {
			
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
