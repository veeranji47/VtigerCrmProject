package pratice.testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GoogleXapth {
	@Test
	public void test() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.google.co.in/");
		
		driver.findElement(By.xpath("//a[@class='gb_A']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//a[@class='tX9u1b']/span[@jsname='V67aGc']"));
		for(int i=0;i<list.size();i++) {
		System.out.println(list.get(i).getText());
			
		}
			
	}

}
