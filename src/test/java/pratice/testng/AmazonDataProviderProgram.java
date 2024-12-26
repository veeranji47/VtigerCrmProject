package pratice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pratice.genericUtilities.ExcelUtility;

public class AmazonDataProviderProgram {
	
	@Test(dataProvider = "getData")
	public void fetchMobilePrice(String brandName, String mbiName) {
		ExcelUtility eLib = new ExcelUtility();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[text()='"+mbiName+"']/ancestor::div[contains(@class,'a-spacing-small')]/descendant::span[@class='a-price-whole']")).getText();
		
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		
		ExcelUtility eLib = new ExcelUtility();
		eLib.intiExcel();
		int size = eLib.rowCount("products");
		Object[][] data = new Object[3][2];
		
		for(int i = 0; i < size; i++) {
			data[i][0] = eLib.getDataFromExcel("products", i+1, 0);
			data[i][1] = eLib.getDataFromExcel("products", i+1, 1);
		}
		
		
		
		return data ;
	}

}
