package pratice.testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Shadow_Element {
	@Test
	public void shadow(){
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demoapps.qspiders.com/ui/shadow?sublist=0");
		
		WebElement ele = driver.findElement(By.xpath("//div[@class='my-3'][1]"));
		SearchContext Shadow1 = ele.getShadowRoot();
		Shadow1.findElement(By.cssSelector("input[placeholder='Enter your username']")).sendKeys("Shiva");
       
		WebElement ele2 = driver.findElement(By.xpath("//div[@class='my-3'][2]"));
		SearchContext Shadow2 = ele2.getShadowRoot();
		Shadow2.findElement(By.cssSelector("input[placeholder='Enter your password']")).sendKeys("Shiva");
		driver.findElement(By.xpath("//button[text()='Login']")).click();	
		
	}


@Test
public void closed() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://demoapps.qspiders.com/ui/shadow/closed?sublist=1");
	
	driver.findElement(By.xpath("//h1gin']")).click();
	Actions act=new Actions(driver);

	
}
}