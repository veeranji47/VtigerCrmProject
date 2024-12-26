package implementation.genericUtility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.genericUtilities.ExcelUtility;
import com.genericUtilities.IConstantPath;
import com.genericUtilities.JavaUtility;
import com.genericUtilities.PropertiesFileUtility;
import com.genericUtilities.WebDriverUtility;



public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException {
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
		driverUtil.waitForBrowserLoad(time);
		driverUtil.navigateApp(url);
		
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
		
		String contactLastName = excelUtil.getDataFromExcel("contact", 7, 3)+jutil.getRandomNum();
		String parentWindowId = driver.getWindowHandle();
		System.out.println(parentWindowId);
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//table[@class='small']/descendant::tr[5]/descendant::img[contains(@src,'images/select.gif')]")).click();
		driverUtil.switchToWindowOnURL("module=Accounts");
		System.out.println("current id : "+driver.getWindowHandle());
		String orgName = excelUtil.getDataFromExcel("contact", 7, 2);
		driver.findElement(By.xpath("//a[contains(.,'"+orgName+"')]")).click();
		
		driver.switchTo().window(parentWindowId);
		
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();
		
		//verify the contact with Organization Name
		String orgNameVerify = driver.findElement(By.xpath("//td[text()='Organization Name']/following::a[contains(.,'"+orgName+"')]")).getText();
		
		if(orgNameVerify.contains(orgName)) {
			excelUtil.setDataIntoExcel("contact", 7, 4, "PASS");
			System.out.println(orgNameVerify+" Testcase verified passed :)");
		}else {
			excelUtil.setDataIntoExcel("contact", 7, 4, "FAIL");
			System.out.println(orgNameVerify+" Testcase failed :( ");
		}
		
		excelUtil.saveToExcel();
		excelUtil.closeExcel();
		
		
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		driverUtil.mouseHover(signOut);
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driverUtil.closeAllWindows();
		
	}

}
