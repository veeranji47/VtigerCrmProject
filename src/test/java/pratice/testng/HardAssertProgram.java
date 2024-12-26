package pratice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAssertProgram {
	
	@Test
	public void homePageVerificationTitleTest() {
		
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		String homeTitle = driver.findElement(By.xpath("//a[contains(.,'Home')]")).getText();
		Assert.assertEquals("Home Page", homeTitle);
	}
	@Test
	public void homePageVerificationLogoTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		WebElement log = driver.findElement(By.xpath("//img[contains(@src,'crm-logo.gif')]"));
		boolean status = log.isDisplayed();
		Assert.assertEquals(true, status);
	}

}
