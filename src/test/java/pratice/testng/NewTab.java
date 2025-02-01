package pratice.testng;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class NewTab {
	
	@Test
	public void newTab() {
		
		 WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demoapps.qspiders.com/ui");

		driver.findElement(By.xpath("//section[text()='Popups']")).click();
		driver.findElement(By.xpath("//section[text()='Browser Windows']")).click();		
		driver.findElement(By.xpath("//a[contains(@href,'newTab?sublist')]")).click();
		
		String parentWindowid = driver.getWindowHandle();
		driver.findElement(By.id("browserButton4")).click();

		Set<String> multipleWindowids = driver.getWindowHandles();
		for(String ids : multipleWindowids) {
			if(!parentWindowid.equals(ids)) {
				driver.switchTo();
				break;
			}
		}
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("veera@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("veera123");
		driver.findElement(By.xpath("//input[@id='confirm-password']")).sendKeys("veera123");
		driver.findElement(By.xpath("//button[text()='Sign Up']")).click();
//		driver.close();
		driver.switchTo().window(parentWindowid);
	}

}
