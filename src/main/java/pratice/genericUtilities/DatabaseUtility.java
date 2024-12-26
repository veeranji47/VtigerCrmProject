package pratice.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection connection;
	Statement statement;
	ResultSet result;
	
	public void initDatabse() {
		Driver driver = null;
		try {
			driver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql//localhost:3306/advsel", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ResultSet getDataFromDatabase(String query) {

		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public int writeDataIntoDatabase(String query) {
		int num = 0;
		try {
			num = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	public void closeConnectionDatabase() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
