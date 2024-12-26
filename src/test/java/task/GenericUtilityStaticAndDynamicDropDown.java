package task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pratice.genericUtilities.WebDriverUtility;

public class GenericUtilityStaticAndDynamicDropDown {

	public static void main(String[] args) {
		WebDriverUtility driverUtil = new WebDriverUtility();
		
		WebDriver driver = driverUtil.launchBrowser("chrome");
		driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html#google_vignette");
//		WebElement add = driver.findElement(By.xpath("//div[@id='dismiss-button']/div/*[name() = 'svg']"));
//		if(add.isDisplayed()) {
//			add.click();
//		}
		
		By path = By.id("course");
		WebElement search = driver.findElement(path);
		String text = "Java";
		driverUtil.handleSaticAndDynamicDropDown( search, text);
	}

}
