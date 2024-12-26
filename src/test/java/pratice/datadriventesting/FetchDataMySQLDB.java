package pratice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchDataMySQLDB {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		try {
			//load / register the driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			//get a connection to the particular database
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
			
			if(connection != null) {
				System.out.println("database connected");
			}else {
				System.out.println("Database not connected");
			}
			// create sql statement
			Statement statement = connection.createStatement();
			// execute query and get result in ResultSet
			ResultSet result = statement.executeQuery("select * from student;");
			//fetch the column names using ResultSetMetaData
			ResultSetMetaData colnames = result.getMetaData();
			int col = colnames.getColumnCount();
			for(int i = 1; i <= col; i++) {
				
				String name = colnames.getColumnName(i);
				System.out.print(name+"\t");
			}
			System.out.println();
			// print all the table data
			while (result.next()) {
				System.out.println(result.getInt("sid") + "\t" + result.getString("sname") + "\t"
						+ result.getString("phNo") + "\t" + result.getString("course") + "\t" + result.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			connection.close();
		}

	}
}
