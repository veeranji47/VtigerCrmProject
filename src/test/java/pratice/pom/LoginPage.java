package pratice.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage {
	
	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	@FindBy(id="submitButton")
	WebElement ele3;
	
	
	@Test
	public void loginTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		
		driver.get("http://localhost:8888/");
		
		lp.ele1.sendKeys("admin");
		lp.ele2.sendKeys("admin");
		
		driver.navigate().refresh();
		
		lp.ele1.sendKeys("admin");
		lp.ele2.sendKeys("admin");
		ele3.click();
		
				
	}

}
