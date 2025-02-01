package pratice.testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Sample12 {
	@Test
	public void test() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.google.co.in/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("//a[@class='gb_A']")).click();
		WebElement list = driver.findElement(By.xpath("//div[@class='LVal7b ']/ul/li/a/span[@class='Rq5Gcb' and .='Account']"));
		String text = list.getAttribute("data-text");
		System.out.println(text);
//		for(int i=0;i<list.size();i++) {
//		System.out.println(list.get(i).getText());
//			
//		}
		
		driver.close();
	}

}
