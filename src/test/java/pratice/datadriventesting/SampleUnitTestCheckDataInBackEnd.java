package pratice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleUnitTestCheckDataInBackEnd {

	@Test
	public void checkData() {
		Connection connection = null;
		try {
			String name = "Chotu";
			boolean flag = false;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("Select * from student;");
			while (result.next()) {
				String expectedName = result.getString("sname");
				if (name.equals(expectedName)) {
					flag = true;
					System.out.println(name + " is a available in database :)");
				}
			}
			if (flag == false) {

				Assert.fail(name + " is not available in database :(");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(connection != null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
