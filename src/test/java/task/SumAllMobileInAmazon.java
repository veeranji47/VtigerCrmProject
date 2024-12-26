package task;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SumAllMobileInAmazon {
	
	@Test
	public void sumAllMobilePrices(){
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphones");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		List<WebElement> mobiles = driver.findElements(By.xpath("//span[contains(@class,'color-base a-text-normal')]/ancestor::div[contains(@class,'spacing-top-small')]/descendant::span[@class='a-price-whole']"));
		
		int sum = 0;
		for(int i = 0; i < mobiles.size(); i++) {
			String price =  mobiles.get(i).getText();
			int p = Integer.parseInt(price);
			sum = sum + p;
		}
		System.out.println(sum);
	}

}
