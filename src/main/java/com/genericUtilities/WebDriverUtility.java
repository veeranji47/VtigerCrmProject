package com.genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This class contains reusable methods to perform the driver operation
 * @author Veera
 */
public class WebDriverUtility {

	WebDriver driver;
	PropertiesFileUtility putil;
	Select select;
	Actions actions;
	
	/**
	 * This method used is to launch the specific browser
	 * @param browser
	 * @return
	 */

	public WebDriver launchBrowser(String browser) {
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("safari")) {
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	/**
	 * This method is used to load all the web elements
	 * @param time
	 */

	public void waitForBrowserLoad(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void navigateApp(String url) {
		driver.get(url);
	}

	public void select(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}

	public void select(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}

	public void select(String text, WebElement element) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void deselectAllOption(WebElement element, int index) {
		select = new Select(element);
		select.deselectAll();
	}
	
	public void mouseHover(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).perform();;
	}
	
	public void clickElement(WebElement element) {
		actions = new Actions(driver);
		actions.click(element).perform();
	}
	
	public void doubleClickOnElement(WebElement element) {
		actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	public void dragAndDropElemet(WebElement source, WebElement destination) {
		actions = new Actions(driver);
		actions.dragAndDrop(source, destination).perform();
	}
	
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(String name) {
		driver.switchTo().frame(name);
	}
	
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public String getParentWindowId() {
		return driver.getWindowHandle();
	}
	
	public void alertHandle(String status) {
		if(status.contains("ok"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void enableElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	
	public void getScreenshotElement(WebElement element, String className, JavaUtility jutil ) {
		
		File temp = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/" + className + "_"+ jutil.getDateAndTime()+".png");
		
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	 
	public String getScreenshotPage(WebDriver driver, String className, JavaUtility jutil ) { 
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./screenshots/" + className + "_"+ jutil.getDateAndTime()+".png");
		
		try {
			FileHandler.copy(temp, perm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return perm.getAbsolutePath();
	}
	
	public void switchToWindowOnURL(String expectedPartialUrl) {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> multipleIDs = ids.iterator();
		while(multipleIDs.hasNext()) {
			driver.switchTo().window(multipleIDs.next());
			if(driver.getCurrentUrl().contains(expectedPartialUrl))
				break;
		}
	}
	
	public void handleSaticAndDynamicDropDown(WebElement dropDown , String value ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		if(dropDown.getTagName().equals("select")) {
			Select select = new Select(dropDown);
			select.selectByVisibleText(value);
		} else {
			System.out.println("dynamic dd");
			dropDown.click();
			dropDown.sendKeys(value, Keys.DOWN, Keys.ENTER);
		}
	}
	
	public void switchToWindowOnTitle(String partialsTitle) {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> windowIds = ids.iterator();
		while(windowIds.hasNext()) {
			driver.switchTo().window(windowIds.next());
			if(driver.getTitle().contains(partialsTitle));
		}
	}
	
	public void closeCurrentWindow() {
		driver.close();
	}
	
	public void closeAllWindows() {
		driver.quit();
	}
	
	
}
