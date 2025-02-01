package pratice.testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FetchDataFromTable {
	@Test
	public void test() {
	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("file:///C:/Users/ASHOK%20KUMAR/Downloads/Webtable.html");
	List<WebElement> tables = driver.findElements(By.tagName("table"));
	for(int i=1;i<=tables.size();i++) {
		 List<WebElement> data = driver.findElements(By.xpath("//table["+i+"]/tbody/tr/td[position()=1 or position()=3]"));
		for(int j=0;j<data.size();j++) {
			
			String text = data.get(j).getText();
			System.out.println(text+"\t");
		}
		System.out.println("---------------------------");
		
	}
	}

}
