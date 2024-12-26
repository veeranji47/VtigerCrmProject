package task;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadDataExcelInsertIntoDatabase {

	@Test
	public void insertData() throws Exception {
		Connection connection = null;
		FileInputStream fis = null;
		Workbook wb = null;
		PreparedStatement preStmt = null;
		
		
			fis = new FileInputStream("./src/test/resources/studentData.xlsx");
			wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("Sheet1");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
			String sql = "insert into student(sid,sname,phNo,course) values(?, ?, ?, ?);";
			preStmt = connection.prepareStatement(sql);
			
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				int col1 =(int) row.getCell(0).getNumericCellValue();
				String col1Data = String.valueOf(col1);
				String col2 = row.getCell(1).getStringCellValue();
				long col3 = (long) row.getCell(2).getNumericCellValue();
				String col3Data = String.valueOf(col3);
				String col4 = row.getCell(3).getStringCellValue();

				System.out.println(col1 + "\t" + col2 + "\t" + col3 + "\t" + col4);

				preStmt.setString(1, col1Data);
				preStmt.setString(2, col2);
				preStmt.setString(3, col3Data);
				preStmt.setString(4, col4);

				preStmt.executeUpdate();

			}
			
		
			wb.close();
			preStmt.close();
			connection.close();
		
	}

}
