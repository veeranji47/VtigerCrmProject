package pratice.genericUtilities;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	WebDriver driver;
	Actions actions;
	Select select;
	
	public void getMaximize() {
		driver.manage().window().maximize();
		
	}
	public WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(browser.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		return driver;
		
	}
	public void switchToWindow(CharSequence partialUrl) {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
			if(driver.getCurrentUrl().contains(partialUrl)) {
				break;
			}
		}
		
	}
	public String getScreenshotPage() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}
	
	public String getScreenshotElement(WebElement element) {
		return element.getScreenshotAs(OutputType.BASE64);
	}
	
	public void handleSaticAndDynamicDropDown(WebElement dropDown , String value ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		if(dropDown.getTagName().equals("select")) {
			Select select = new Select(dropDown);
			select.selectByVisibleText(value);
		} else {
			System.out.println("dynamic dd");
			dropDown.click();
			dropDown.sendKeys(value);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			dropDown.sendKeys( Keys.DOWN, Keys.ENTER);
		}
	}
	
//	public void selectDropdown() {
//		
//        if (isStatic) {
//            WebElement dropdown = driver.findElement(locator);
//            Select select = new Select(dropdown);
//            select.selectByVisibleText(value);
//        } else {
//           WebElement ele1 =  driver.findElement(locator); 
//           ele1.sendKeys(value, Keys.ENTER );
//           // driver.findElement(By.xpath("//ul[@class='G43f7e']/descendant::div[contains(@aria-label,'"+value+"')]/div/span[.='"+value+"']")).click();
//        }
 //   }
	
	
}
