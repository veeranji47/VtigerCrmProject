package pratice.testng;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenLinks {
	
	@Test
	public void allLinks() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get("https://www.amazon.in/");
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		
		System.out.println(allLinks.size());
		
		for( WebElement link : allLinks) {
			String value = link.getAttribute("href");
			if(value==null || value.isBlank()) {
				System.out.println("We cant check");
				continue;
			}
			try {
				URL url = new URL(value);
				
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.connect();
				if(con.getResponseCode() >= 400) {
					System.out.println(value + " ---> " + con.getResponseCode()+ " it is a broken link  "+con.getResponseMessage());
				}else {
					System.out.println("-----------Working--------------");
					System.out.println(value + "  is working ");
					System.out.println("-----------Working--------------");
					
				}
				
			}
			catch(Exception e) {
				
				
			}
		}
		
	}

}
