package implementation.genericUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.genericUtilities.ExcelUtility;
import com.genericUtilities.IConstantPath;
import com.genericUtilities.JavaUtility;
import com.genericUtilities.PropertiesFileUtility;
import com.genericUtilities.WebDriverUtility;



public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverUtility driverUtil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelUtility excelUtil = new ExcelUtility();
		
		putil.propertiesInit(IConstantPath.propertiesPath);
		excelUtil.excelInit(IConstantPath.excelPath);

		String Browser = putil.getDataFromPropertiesFile("browser");
		String url = putil.getDataFromPropertiesFile("url");
		String un = putil.getDataFromPropertiesFile("username");
		String psw = putil.getDataFromPropertiesFile("password");
		String timeout = putil.getDataFromPropertiesFile("timeouts");
		int time = Integer.parseInt(timeout);
		
		
		WebDriver driver = driverUtil.launchBrowser(Browser);
		
		driverUtil.maximizeWindow();
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
		
		String currentDate = jutil.getCurrentDateYYYYMMDD();
		String reqDate = jutil.getRequiredDate(30);
		
		String contactLastName = excelUtil.getDataFromExcel("contact", 4, 2)+jutil.getRandomNum();
		
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
		
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		driverUtil.mouseHover(signOut);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		if(welPageTitle.contains("Commercial")) {
			excelUtil.setDataIntoExcel("contact", 4, 4, "PASS");
			System.out.println("Logged out successfully");
		}else {
			excelUtil.setDataIntoExcel("contact", 4, 4, "FAIL");
			System.out.println("Login page not displayed...(");
		}
		
		excelUtil.saveToExcel();
		excelUtil.closeExcel();
		driverUtil.closeAllWindows();
		
	}

}
