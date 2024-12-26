package task;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class InsertDataOtherDatabse {
	
	@Test
	public void insertData() {
		Connection connection = null;
		FileInputStream fis = null;
		Workbook wb = null;
		
		try {
			fis = new FileInputStream("./src/test/resources/studentData.xlsx");
			wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Sheet3");
			
			connection = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
			
			if(connection != null) {
				System.out.println("database connected ");
			}else {
				System.out.println("database not connected");
			}
			String sql = "insert into project(project_id,created_by,created_on,project_name,status,team_size) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement preStmt = connection.prepareStatement(sql);
			int count = 0;
			for(Row row : sheet) {
				if(row.getRowNum() == 0) {
					continue;
				}
				String col1 = row.getCell(0).toString();
				String col2 = row.getCell(1).toString();
				String col3 = row.getCell(2).toString();
				String col4 = row.getCell(3).toString();
				String col5 = row.getCell(4).toString();
				int col6Data = (int) row.getCell(5).getNumericCellValue();
				String col6 = String.valueOf(col6Data);
				
				
				//System.out.println(col1 + "\t" + col2 + "\t" + col3 + "\t" + col4 + "\t" + col5 + "\t" + col6 );
				preStmt.setString(1, col1);
				preStmt.setString(2, col2);
				preStmt.setString(3, col3);
				preStmt.setString(4, col4);
				preStmt.setString(5, col5);
				preStmt.setString(6, col6);
				
				
				preStmt.executeUpdate();
				
				String selectQuery = "select * from products;";
				ResultSet result = preStmt.executeQuery();
				ResultSetMetaData colNames = result.getMetaData();
				int noOfCol = colNames.getColumnCount();
				for(int i = 0; i < noOfCol; i++) {
					String name = colNames.getColumnName(i);
					System.out.print(name + "\t");
				}
				while(result.next()) {
					
					String name = result.getString("created_by");
					if(name.contains(col2)) {
						count++;
					}
				}
				
				
			}
			System.out.println(count);
	
			
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
