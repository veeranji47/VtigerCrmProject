package task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchEntireColumnDataFromExcel {

	public static void main(String[] args) throws IOException {
		int colIndex = 1;
		FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheet = wb.getSheet("MultipleData");
		Iterator<Row> it = sheet.iterator();
		while(it.hasNext()) {
			Row row = it.next();
			Cell cell = row.getCell(colIndex);
			
			switch(cell.getCellType()) {
				case STRING : System.out.println(cell.getStringCellValue());
					break;
				case NUMERIC : System.out.println((int)cell.getNumericCellValue());
					break;
			}
			
		}
		
		
	}

}
