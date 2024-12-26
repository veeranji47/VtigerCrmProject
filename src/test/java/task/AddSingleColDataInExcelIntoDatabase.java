package task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class AddSingleColDataInExcelIntoDatabase {
	Connection connection = null;
	PreparedStatement preStmt;
	FileInputStream fis;
	Workbook wb;

	@Test
	public void addColumn() throws Exception {
		

			fis = new FileInputStream("./src/test/resources/studentData.xlsx");
			wb = WorkbookFactory.create(fis);

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
			if (connection != null) {
				System.out.println("database is connected");
			}
			String sql = "update student set email = ? where sid = ? ;" ;
			preStmt = connection.prepareStatement(sql);
			

			Sheet sheet = wb.getSheet("Sheet2");
			System.out.println(sheet.getSheetName().toString());
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue;
				}
				Cell sidCell = row.getCell(0);
                Cell emailCell = row.getCell(1);

                if (sidCell != null && emailCell != null) {
                    int sidCol = (int) sidCell.getNumericCellValue();
                    String col1 = String.valueOf(sidCol);
                    String emailCol = emailCell.toString();	
                    System.out.println(sidCol + "\t" + emailCol);
                    
                    preStmt.setString(1, col1);
                    preStmt.setString(2, emailCol);
                    
                    preStmt.executeUpdate();
                }
                else {
                    System.out.println("Skipped row " + row.getRowNum() + " due to missing data.");
                }
			}
			
			wb.close();
			connection.close();
	}

}
