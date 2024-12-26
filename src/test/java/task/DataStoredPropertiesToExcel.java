package task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import pratice.datadriventesting.RandomGen;

public class DataStoredPropertiesToExcel {
	
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/task.properties");
		FileInputStream fis2 = new FileInputStream("./src/test/resources/data123.xlsx");
		
		Properties property = new Properties();
		property.load(fis);
		
		Set<Object> data = property.keySet();
		
		int n = data.size();

		RandomGen rg = new RandomGen();
		
		Workbook wb = WorkbookFactory.create(fis2);
		Sheet sheet1 = wb.getSheet("Sheet1");
		Sheet sheet2 = wb.createSheet("Sheet2");
		Sheet sheet3 = wb.createSheet("Sheet3");
		
		int rowNum = 0, rows = n/2, i=0;
		for(Object obj : data) {
				
	            if(rows > rowNum) {
	            	 Row row = sheet1.createRow(rowNum++);
	 	            row.createCell(0).setCellValue(property.getProperty(obj.toString()));
	            }else {
	            	Row row = sheet2.createRow(i++);
	 	            row.createCell(0).setCellValue(property.getProperty(obj.toString()));
	            }
		}

		FileOutputStream fos = new FileOutputStream("./src/test/resources/data123.xlsx");
		wb.write(fos);
		int rowNumber = 0, m = n/2;
		int size = sheet1.getLastRowNum()+sheet2.getLastRowNum();
		for(int j = 0; j <= size; j++) {
			if(m > j)
				sheet3.createRow(j).createCell(j).setCellValue(sheet1.getRow(j).getCell(j).toString());
			else
				sheet3.createRow(j).createCell(j).setCellValue(sheet2.getRow(j).getCell(j).toString());
			
		}
		
		FileOutputStream fos1 = new FileOutputStream("./src/test/resources/data123.xlsx");
		wb.write(fos1);
		wb.close();
		

	}

}
