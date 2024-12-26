package implementation.pomclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crem.basetest.BaseClass;
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



public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() {
		
		Assert.assertTrue(driver.getTitle().contains("Home"));
	
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
			
		String contactLastName = eLib.getDataFromExcel("contact", 1, 2);
		createCnt.getLstName().sendKeys(contactLastName);
		createCnt.clickSaveBtn();
		
		//verify the header
		String title = cntInfo.getHeader().getText();
		
		if(title.contains(contactLastName)) {
			excelUtil.setDataIntoExcel("contact", 1, 4, "PASS");
			System.out.println("Test case passed :)");
		}else {
			excelUtil.setDataIntoExcel("contact", 1, 4, "FAIL");
			System.out.println("Test case failed :( ");
		}
		
		excelUtil.saveToExcel();
		excelUtil.closeExcel();
	
		home.signOutOfVtiger(driverUtil);
		
		driverUtil.closeAllWindows();
		
	}

}
