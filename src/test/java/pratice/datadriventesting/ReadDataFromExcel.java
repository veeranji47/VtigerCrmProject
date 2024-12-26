	package pratice.datadriventesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
	public static void main(String[] args) throws EncryptedDocumentException, IOException  {
		// 1. Read the physical file into java readable format
		FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		int last = wb.getSheet("MultipleData").getRow(0).getLastCellNum();
		
		
		
//		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
//			String productCat =  wb.getSheet("MultipleData").getRow(i).getCell(0).toString();
//			String productName =  wb.getSheet("MultipleData").getRow(i).getCell(1).toString();
//			
//			System.out.println(productCat + "     " + productName);
//		}
		
		
		wb.close();
		
		
	}
}
