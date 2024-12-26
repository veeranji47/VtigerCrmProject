package implementation.datadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("./commonData.properties");
		
		Properties property = new Properties();
		property.load(fis);
		
		System.out.println(property.getProperty("browser"));
		String url = property.getProperty("url");
		String un = property.getProperty("username");
		String psw = property.getProperty("password");
		String timeout = property.getProperty("timeouts");
		long time = Long.parseLong(timeout);
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(url);
		
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(psw);
		driver.findElement(By.id("submitButton")).click();
				
	}

}
