package implementation.pomclasses;

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

public class DeleteOrgName {
	
	public static void main(String[] args) {
		WebDriverUtility driverUtil = new WebDriverUtility();
		PropertiesFileUtility fileUtil = new PropertiesFileUtility();
		ExcelUtility xutil = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		String propertiesPath = IConstantPath.propertiesPath;
		String excelPath = IConstantPath.excelPath;
		
		fileUtil.propertiesInit(propertiesPath);
		xutil.excelInit(excelPath);
		String Browser = fileUtil.getDataFromPropertiesFile("browser");
		String url = fileUtil.getDataFromPropertiesFile("url");
		String un = fileUtil.getDataFromPropertiesFile("username");
		String psw = fileUtil.getDataFromPropertiesFile("password");
		String timeouts = fileUtil.getDataFromPropertiesFile("timeouts");
		long time = Long.parseLong(timeouts);
		
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
		home.clickOrgLnk();
		org.getCretaeOrgBtn();
		
		String orgName = xutil.getDataFromExcel("org", 10, 2)+jutil.getRandomNum();
		String num = xutil.getDataFromExcel("org", 10, 3);
		createOrg.getOrgName().sendKeys(orgName);
		createOrg.getPhnNum().sendKeys(num);
		createOrg.clickSaveBtn();
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//td[@class='tabSelected']/a[.='Organizations']")).click();
		
		org.getSearchBar().sendKeys(orgName);
		WebElement searchDropdown = org.getSearchDD();
		driverUtil.select("Organization Name", searchDropdown);
		org.getSearchBarBtn();
		
		//verification organization is added or not
		String orgVerify = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr/td/a[.='"+orgName+"']")).getText();
		if(orgName.equals(orgVerify)) {
			System.out.println("Organization is added to Vtiger : "+orgVerify);
		}else {
			System.out.println("Not added : "+orgVerify);
		}
		
		// delete the organization
		
		driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[4]/td/a[.='del']")).click();
		driverUtil.alertHandle("ok");
		if(orgName.equals(orgVerify)) {
			System.out.println("Organization is not deleted to Vtiger : "+orgVerify);
		}else {
			System.out.println("Deleted to Vtiger : "+orgVerify);
		}
		
		home.signOutOfVtiger(driverUtil);
		
		if(welPageTitle.contains("Commercial")) {
			xutil.setDataIntoExcel("org", 10, 5, "PASS");
			System.out.println("Logged out successfully");
		}else {
			xutil.setDataIntoExcel("org", 10, 5, "Fail");
			System.out.println("Login page not displayed...(");
		}
		
		xutil.saveToExcel();
		xutil.closeExcel();
		driverUtil.closeAllWindows();
	}

}
