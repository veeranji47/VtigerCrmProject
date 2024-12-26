package pratice.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains excel reusable methods to perform the action on excel file
 */

public class ExcelUtility {
	
	FileInputStream fis;
	Workbook wb;
	DataFormatter df;
	
	/**
	 * This method is used to initialize the excel path
	 */
	public void intiExcel() {
		try {
			fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");
			
				try {
					wb = WorkbookFactory.create(fis);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		df = new DataFormatter();
		
	}
	
	/**
	 * This method fetch the data from excel file
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return String
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) {
		return df.formatCellValue( wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
	}
	
	public void writeIntoExcel(String sheetName, int rowNum, int cellNum, String value) {
		wb.getSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
	}
	
	public int rowCount(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	public void saveExcel() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("./src/test/resources/testScriptData.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
	
