package pratice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataWithCondition {

	public static void main(String[] args) throws IOException {
		
		String exceptedString = "tc_21";
		boolean flag = false;
		String tcName = "";
		String orgName = "";
		String type = "";
		
		FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sheet =  wb.getSheet("Condition");
		
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			try {
				String data = sheet.getRow(i).getCell(0).toString();
			if(data.equals(exceptedString)) {
				flag = true;
				tcName = sheet.getRow(i).getCell(1).toString();
				orgName = sheet.getRow(i).getCell(2).toString();
				type = sheet.getRow(i).getCell(3).toString();
			}
			}
			catch (Exception e) {}
		}
		if(flag == true) {
			System.out.println(exceptedString + "\t" + tcName + "\t" + orgName + "\t" + type);			
		}
		else {
			System.out.println(exceptedString + " testcase id data unavailable ");
		}
	}

}
