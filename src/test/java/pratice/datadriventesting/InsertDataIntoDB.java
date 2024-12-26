package pratice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertDataIntoDB {

	public static void main(String[] args) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		
		Statement statement = connection.createStatement();
		
		int result = statement.executeUpdate("insert into student(sid,sname,phNo,course) values(105,\"Chotu\",9988774455,\"Java\")");
		
		System.out.println(result);
	}

}
