package com.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Veera
 * This class contains the reusable methods to read/write from excel
 */
public class ExcelUtility {
	FileInputStream fis;
	Workbook wb;
	FileOutputStream fos;
	DataFormatter df;
	/**
	 * This method is used to initialize the excel path 
	 * @param excelPath
	 */
	
	public void excelInit(String excelPath) {
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		df = new DataFormatter();
		
	}
	
	/**
	 * This method fetch the data from specific cell. Provides the sheetName, 
	 * Which row number and which column number. It will gets particular cell data
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @return
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) {
		String data = df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(colNum));
		return data;
	}
	/**
	 * This method fetch the data from specific test case
	 * @param sheetName
	 * @param exceptedTestName
	 * @return
	 */
	
	public Map<String, String> getDataFromExcel(String sheetName, String exceptedTestName) {
		Map<String, String> map = new HashMap<String, String>();
		int requiredRowNum = 0;
		Sheet sheet = wb.getSheet(sheetName);
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			requiredRowNum = i;
			if(df.formatCellValue( sheet.getRow(i).getCell(1)).equalsIgnoreCase(exceptedTestName))
				break;
		}
		for(int i = requiredRowNum; i <= sheet.getLastRowNum(); i++) {
			if(df.formatCellValue(sheet.getRow(i).getCell(2)).equals("####")) 
				break;
			map.put(df.formatCellValue(sheet.getRow(i).getCell(2)), 
						df.formatCellValue(sheet.getRow(i).getCell(3)));
		}
		return map;
		
	}
	
	/**
	 * This method returns the how many rows in a sheet
	 * @param sheetName
	 * @return int
	 */
	
	public int getRowCount(String sheetName) {
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
		
	}
	
	/**
	 * This method uses write data into excel
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @param value
	 */
	public void setDataIntoExcel(String sheetName, int rowNum, int colNum, String value) {
		wb.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(value);	
	}
	
	/**
	 * This method used to save the data into the excel file
	 */
	public void saveToExcel() {
		try {
			fos = new FileOutputStream("./resourse/testScriptData.xlsx");
			wb.write(fos);
			
		} catch (Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This method is used to close the excel file 
	 */
	public void closeExcel() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
