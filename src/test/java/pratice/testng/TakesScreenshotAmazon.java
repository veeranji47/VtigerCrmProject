package pratice.testng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TakesScreenshotAmazon {
	@Test
	public void takesScreenshotAmazonTest(Method mt) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(srcFile, new File("./screenshot/"+mt.getName()+".png"));
		
	}

}
