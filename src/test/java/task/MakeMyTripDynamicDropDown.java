package task;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.genericUtilities.WebDriverUtility;

public class MakeMyTripDynamicDropDown {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverUtility udriver = new WebDriverUtility();
		String data = "Mumbai";
		WebDriver driver = udriver.launchBrowser("chrome");
		udriver.navigateApp("https://www.makemytrip.com/flights/");;
		Thread.sleep(2000);
		driver.findElement(By.className("commonModal__close")).click();
		
		
		WebElement fromLabel = driver.findElement(By.id("fromCity"));
		udriver.handleSaticAndDynamicDropDown(fromLabel, data);
		
//		Thread.sleep(900);
//		from.click();
	}

}
