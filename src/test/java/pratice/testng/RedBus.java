package pratice.testng;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RedBus {
	
	@Test
	public void test() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.redbus.in/search?fromCityName=Kukatpally,%20Hyderabad&fromCityId=66030&srcCountry=IND&toCityName=Bangalore&toCityId=122&destCountry=IND&onward=20-Jan-2025&opId=0&busType=Any");
		//driver.findElement(By.xpath("//div[.='DEPARTURE TIME']/following-sibling::ul/li[@class='checkbox selected']/input[@id='dt12 pm to 6 pm']")).click();
		List<WebElement> buses = driver.findElements(By.xpath("//div[@class='clearfix row-one ']"));
		System.out.println(buses.size());
		for(int i=0;i<buses.size();i++) {
			String  busName = buses.get(i).findElement(By.xpath("(//div[@class='travels lh-24 f-bold d-color'])["+i+1+"]")).getText();
			String cost = buses.get(i).findElement(By.xpath("//div[text()='"+busName+"']/ancestor::div[@class='clearfix row-one ']/descendant::div[@class='fare d-block']/span")).toString();
			int price = Integer.parseInt(cost);
			System.out.println(price);
		}
		
		
		driver.close();
	
	}

}
