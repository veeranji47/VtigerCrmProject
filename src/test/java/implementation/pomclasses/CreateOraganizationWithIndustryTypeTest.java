package implementation.pomclasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.genericUtilities.ExcelUtility;
import com.genericUtilities.IConstantPath;
import com.genericUtilities.JavaUtility;
import com.genericUtilities.PropertiesFileUtility;
import com.genericUtilities.WebDriverUtility;

import implementaion.objectrepositoryutility.CreatingNewOrganizationPage;
import implementaion.objectrepositoryutility.HomePage;
import implementaion.objectrepositoryutility.LoginPage;
import implementaion.objectrepositoryutility.OrganizationInformationPage;
import implementaion.objectrepositoryutility.OrganizationPage;

public class CreateOraganizationWithIndustryTypeTest {

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
		OrganizationPage org = new OrganizationPage(driver);
		CreatingNewOrganizationPage createOrg = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage orgInfo = new OrganizationInformationPage(driver);
		
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
	
		home.clickOrgLnk();
		
		String orgPageTitle = driver.getTitle();
		if(orgPageTitle.contains("Organization")) {
			System.out.println("Organization Page displayed...");
		}else {
			System.out.println("Organization page not displayed...(");
		}
		
		org.getCretaeOrgBtn();
		
		String createOrgHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createOrgHeader.contains("Creating")) {
			System.out.println("Creating new organization Page displayed...");
		}else {
			System.out.println("Creating new organization page not displayed...(");
		}
		
		String orgName = excelUtil.getDataFromExcel("org", 4, 2)+jutil.getRandomNum();
		String industryText = excelUtil.getDataFromExcel("org", 4, 3);
		String typeText = excelUtil.getDataFromExcel("org", 4, 4);
		
		createOrg.getOrgName().sendKeys(orgName);
		WebElement industry = createOrg.getIndustryName();
		driverUtil.select(industryText, industry);
		WebElement type = createOrg.getAcType();
		driverUtil.select(typeText, type);
		
		createOrg.clickSaveBtn();
		
		//verify the header
		
		String industryVerify = orgInfo.getIndustryCheck().getText();
		String typeVerify = orgInfo.getTypeCheck().getText();
		
		
		if(industryVerify.contains(industryText)) {
			System.out.println("Industry information verified :) " + industryVerify);
		}else {
			excelUtil.setDataIntoExcel("org", 4, 5, "FAIL");
			System.out.println("Industry verify  failed :( " + typeVerify);
		}
		if(typeVerify.contains(typeText)) {
			System.out.println("Type information verified :) ");
		}else {
			excelUtil.setDataIntoExcel("org", 4, 5, "FAIL");
			System.out.println("type verify  failed :( ");
		}
		
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		driverUtil.mouseHover(signOut);
		
		
		
		if(welPageTitle.contains("Commercial")) {
			excelUtil.setDataIntoExcel("org", 4, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			System.out.println("Login page not displayed...(");
		}
		
		excelUtil.saveToExcel();
		excelUtil.closeExcel();
		driverUtil.closeAllWindows();
		
	}

}
