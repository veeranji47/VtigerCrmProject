package implementation.pomclasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ContactPage contact = new ContactPage(driver);
		CreatingNewContactPage createCnt = new CreatingNewContactPage(driver);
		ContactInformationPage cntInfo = new ContactInformationPage(driver);
		
		driverUtil.maximizeWindow();
		driverUtil.waitForBrowserLoad(time);
		driverUtil.navigateApp(url);
		
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
	
		home.clickContactLnk();
		
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
		
		String contactLastName = excelUtil.getDataFromExcel("contact", 7, 3)+jutil.getRandomNum();
		String parentWindowId = driver.getWindowHandle();
		System.out.println(parentWindowId);
		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.getOrgNamePlusBtn();
		driverUtil.switchToWindowOnURL("module=Accounts");
		System.out.println("current id : "+driver.getWindowHandle());
		String orgName = excelUtil.getDataFromExcel("contact", 7, 2);
		driver.findElement(By.xpath("//a[contains(.,'"+orgName+"')]")).click();
		
		driver.switchTo().window(parentWindowId);
		
		createCnt.clickSaveBtn();
		
		//verify the contact with Organization Name
		String orgNameVerify =  cntInfo.getOrgNameTxt().getText();
		
		if(orgNameVerify.contains(orgName)) {
			excelUtil.setDataIntoExcel("contact", 7, 4, "PASS");
			System.out.println(orgNameVerify+" Testcase verified passed :)");
		}else {
			excelUtil.setDataIntoExcel("contact", 7, 4, "FAIL");
			System.out.println(orgNameVerify+" Testcase failed :( ");
		}
		
		excelUtil.saveToExcel();
		excelUtil.closeExcel();
		
		home.signOutOfVtiger(driverUtil);
		
		driverUtil.closeAllWindows();
		
	}

}
