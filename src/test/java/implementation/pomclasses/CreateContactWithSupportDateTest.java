package implementation.pomclasses;

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

import implementaion.objectrepositoryutility.ContactInformationPage;
import implementaion.objectrepositoryutility.ContactPage;
import implementaion.objectrepositoryutility.CreatingNewContactPage;
import implementaion.objectrepositoryutility.HomePage;
import implementaion.objectrepositoryutility.LoginPage;

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
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ContactPage contact = new ContactPage(driver);
		CreatingNewContactPage createCnt = new CreatingNewContactPage(driver);
		ContactInformationPage cntInfo = new ContactInformationPage(driver);
		
		driverUtil.maximizeWindow();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(url);
		
		String welPageTitle = driver.getTitle();
		if(welPageTitle.contains("Commercial")) {
			System.out.println("Login Page displayed...");
		}else {
			System.out.println("Login page not displayed...(");
		}
		
		login.loginToVtiger(un, psw);
		
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
		contact.clickCretaeOrgBtn();
		
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
		createCnt.getLstName().sendKeys(contactLastName);
		Thread.sleep(800);
		
		WebElement presentDate = createCnt.getStartDateEdt();
		presentDate.clear();
		Thread.sleep(800);
		presentDate.sendKeys(currentDate);
		Thread.sleep(800);
		
		WebElement exeDate = createCnt.getEndDateEdt();
		exeDate.clear();
		Thread.sleep(800);
		exeDate.sendKeys(reqDate);
		createCnt.clickSaveBtn();

		//verify the header
		String verifyCurDt = cntInfo.getStartDateText().getText();
		String verifyreqDt = cntInfo.getEndDateText().getText();
		
		if(verifyCurDt.contains(currentDate)) {
			
			System.out.println("Current Date information added successfully");
		}else {
			System.out.println("Current Date information not added successfully");
		}
		if(verifyreqDt.contains(reqDate)) {
			System.out.println("Support Date information added successfully");
		}else {
			
			System.out.println("Support Date information not added successfully");
		}
		
		home.signOutOfVtiger(driverUtil);
		
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
