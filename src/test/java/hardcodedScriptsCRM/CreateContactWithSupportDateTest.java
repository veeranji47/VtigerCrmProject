package hardcodedScriptsCRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis = new FileInputStream("./commonData.properties");
		FileInputStream fis2 = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis2);
		
		RandomGen rg = new RandomGen();
		Properties property = new Properties();
		property.load(fis);
		
		String Browser = property.getProperty("browser");
		String url = property.getProperty("url");
		String un = property.getProperty("username");
		String psw = property.getProperty("password");
		String timeout = property.getProperty("timeouts");
		int time = Integer.parseInt(timeout);
		
		
		WebDriver driver = null;
		if(Browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(Browser.equals("Edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid browser :(");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(url);
		
		String welPageTitle = driver.getTitle();
		if(welPageTitle.contains("Commercial")) {
			System.out.println("Login Page displayed...");
		}else {
			System.out.println("Login page not displayed...(");
		}
		
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(psw);
		driver.findElement(By.id("submitButton")).click();
		
		String homePageTitle = driver.getTitle();
		if(homePageTitle.contains("Home")) {
			System.out.println("Home Page displayed...");
		}else {
			System.out.println("Home page not displayed...(");
		}
	
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		String contactPageTitle = driver.getTitle();
		if(contactPageTitle.contains("Contacts")) {
			System.out.println("Contacts Page displayed...");
		}else {
			System.out.println("Contacts page not displayed...(");
		}
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		String createContactHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createContactHeader.contains("Creating")) {
			System.out.println("Creating new Contact Page displayed...");
		}else {
			System.out.println("Creating new Contact page not displayed...(");
		}
		
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(dateObj);
		
		Calendar cal =sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String reqDate = sdf.format(cal.getTime());
		
		String contactLastName = wb.getSheet("contact").getRow(4).getCell(2).toString()+rg.randomNum();
		
		System.out.println(contactLastName);
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		Thread.sleep(800);
		WebElement presentDate = driver.findElement(By.name("support_start_date"));
		presentDate.clear();
		Thread.sleep(800);
		presentDate.sendKeys(currentDate);
		Thread.sleep(800);
		
		WebElement exeDate = driver.findElement(By.name("support_end_date"));
		exeDate.clear();
		Thread.sleep(800);
		exeDate.sendKeys(reqDate);
		
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();
		
		//verify the header
		String verifyCurDt = driver.findElement(By.xpath("//td[@id='mouseArea_Support Start Date']")).getText();
		String verifyreqDt = driver.findElement(By.xpath("//td[@id='mouseArea_Support End Date']")).getText();
		
		if(verifyCurDt.contains(currentDate)) {
			
			System.out.println("Current Date information added successfully");
		}else {
			System.out.println("Support Date information not added successfully");
		}
		if(verifyCurDt.contains(currentDate)) {
			System.out.println("Current Date information added successfully");
		}else {
			
			System.out.println("support Date information not added successfully");
		}
		
		
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		actions.moveToElement(signOut).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		if(welPageTitle.contains("Commercial")) {
			wb.getSheet("create").getRow(4).createCell(4).setCellValue("PASS");
			System.out.println("Logged out successfully");
		}else {
			wb.getSheet("create").getRow(4).createCell(4).setCellValue("FAIL");
			System.out.println("Login page not displayed...(");
		}
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		
		driver.close();
		
	}

}
