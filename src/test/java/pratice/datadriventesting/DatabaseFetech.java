package pratice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFetech {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3333/project", "root@%", "root");
		
		Statement stmt = connection.createStatement();
		System.out.println("connected");
	//	stmt.executeQuery("select ")
	}

}
