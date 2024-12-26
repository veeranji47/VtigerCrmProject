package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchDataOtherDatabase {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		try {
			// load / register the driver
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			// get a connection to the particular database
			connection = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");

			if (connection != null) {
				System.out.println("database connected");
			} else {
				System.out.println("Not connected ");
			}
			// create sql statement
			Statement statement = connection.createStatement();
			// execute query and get result in ResultSet
			ResultSet result = statement.executeQuery("select * from project;");
			// fetch the column names using ResultSetMetaData
			ResultSetMetaData colnames = result.getMetaData();
			int col = colnames.getColumnCount();
			for (int i = 1; i <= col; i++) {

				String name = colnames.getColumnName(i);
				System.out.print(name + "\t");
			}
			System.out.println();
			// print all the table data
			while (result.next()) {
				System.out.println(result.getString("project_id") + "\t" + result.getString("created_by") + "\t"
						+ result.getString("created_on") + "\t" + result.getString("project_name") + "\t"
						+ result.getString("status") + "\t" + result.getString("team_size"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}

	}
}
