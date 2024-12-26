package implementation.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import pratice.datadriventesting.RandomGen;

public class ImplementJsonFileVtiger {

	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser json = new JSONParser();
		Object object = json.parse(new FileReader("./src/test/resources/commonData.json"));
		JSONObject jo = (JSONObject) object;
		
		String Browser =  jo.get("browser").toString();
		String url =  jo.get("url").toString();
		String  username =  jo.get("username").toString();
		String password =  jo.get("password").toString();
		String timeouts =jo.get("timeouts").toString();
		
		int time = Integer.parseInt(timeouts);
		
		RandomGen random = new RandomGen();
		StringBuilder randomNum = random.randomAlphaNum();
		
		FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String orgName = wb.getSheet("Condition").getRow(1).getCell(2).toString() + randomNum;
		
		WebDriver driver = null;
		if(Browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(Browser.equals("firefox")) {

		}else if(Browser.equals("Edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid browser :(");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
	
		driver.findElement(By.xpath("//a[.='Organizations' and contains(@href,'action=index')]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();
		
		//verify the header
		String title = driver.findElement(By.className("dvHeaderText")).getText();
		
		if(title.contains(orgName)) {
			wb.getSheet("Condition").getRow(1).createCell(4).setCellValue("PASS");
			System.out.println("Test case passed :)");
		}else {
			wb.getSheet("Condition").getRow(1).getCell(4).setCellValue("FAIL");
			System.out.println("Test case failed :( ");
		}
		
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		actions.moveToElement(signOut).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.close();
		//convert the file readable format to write / editable format an excel file
		FileOutputStream fos = new FileOutputStream("./src/test/resources/data.xlsx");
		//save the modified data into an excel
		wb.write(fos);
		//close the excel file
		wb.close();
	}

}
