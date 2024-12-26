package pratice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataExcel {
	
	public static void main(String[] args) throws Exception, IOException {
		//get the file into java representation object
		FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
		//Open the excel file and readable format
		Workbook wb = WorkbookFactory.create(fis);
		
		//set the data into particular cell and specify the datatype of that cell data
		Cell cell = wb.getSheet("Condition").getRow(0).createCell(4);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Status");
		
		Sheet sheet = wb.getSheet("Condition");
		
		System.out.println(sheet.getRow(0).getCell(0).toString());
		String tcidData = "";
		String excTcId = "tc_03";
		String tcName = "";
		String orgName = "";
		String type = "";
		String status ="";
		boolean flag = false;
		
		// fetch the particular testcase details into an excel file and write data back to excel status is pass or fail
		for(int i = 0; i <= sheet.getLastRowNum(); i++) {
			try {
			tcidData = sheet.getRow(i).getCell(0).toString();
			if(tcidData.equals(excTcId)) {
				flag = true;
				tcName = sheet.getRow(i).getCell(1).toString();
				orgName = sheet.getRow(i).getCell(2).toString();
				type = sheet.getRow(i).getCell(3).toString();
				
				Cell cellData = wb.getSheet("Condition").getRow(i).createCell(4);
				cellData.setCellType(CellType.STRING);
				cellData.setCellValue("Fail");	
				
				status = sheet.getRow(i).getCell(4).toString();
			}		
		}
		
		catch (Exception e) {}
		}
		
		if(flag == true) {
			System.out.println("TestCase data : ");
			System.out.println(excTcId + "\t" + tcName + "\t" + orgName + "\t" + type + "\t" + status);
		}else {
			System.out.println(excTcId + " unavailable Testcase data");
		}
		
		//convert the file readable format to write / editable format an excel file
		FileOutputStream fos = new FileOutputStream("./src/test/resources/data.xlsx");
		//save the modified data into an excel
		wb.write(fos);
		//close the excel file
		wb.close();
		
	}

}
