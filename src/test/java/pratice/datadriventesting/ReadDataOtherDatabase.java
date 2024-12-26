package pratice.datadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReadDataOtherDatabase {

	public static void main(String[] args) throws SQLException {
		String projectName = "TekPyramid";
		Connection con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		if(con != null) {
			System.out.println("connected database : ");
		}else {
			System.out.println("Not connected");
		}
		String query = "Insert into project(project_id, created_by, created_on, project_name, status, team_size) values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "10114");
		stmt.setString(2, "Veera");
		stmt.setString(3, "10-09-2024");
		stmt.setString(4, "TekPyramid");
		stmt.setString(5, "On going");
		stmt.setString(6, "4");
		
		int result = stmt.executeUpdate();
		if(result > 0) {
			System.out.println("Inserted row "+result);
		}else {
			System.out.println(" no rows inserted");
		}
		
		String selQuery = "select * from project";
		ResultSet set = stmt.executeQuery(selQuery);
		ResultSetMetaData cols = set.getMetaData();
		int cloSize = cols.getColumnCount();
		for(int i = 1; i <= cloSize; i++) {
			System.out.print(cols.getColumnName(i) + "\t");
		}
		System.out.println();
		
		while(set.next()) {
			String data = set.getString("project_name");
			if(data.contains(projectName))
			System.out.println(set.getString("project_id") + "\t" + set.getString("project_name") 
			+ "\t" + set.getString("status"));
		}
		
		
		con.close();
		
	}

}
