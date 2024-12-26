package task;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramLikeOnePost {
	
	public static void main(String[] args) throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.instagram.com/accounts/login/?hl=en");
		
		driver.findElement(By.name("username")).sendKeys("");
		driver.findElement(By.name("password")).sendKeys("");
		driver.findElement(By.xpath("//button[.='Log in']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[.='Not now' and @role='button']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.='Not Now']")).click();
		
		driver.findElement(By.xpath("//span[.='Search' and contains(@class,'xlyipyv')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("qspiders");
		driver.findElement(By.xpath("//span[.='qspiders_jntu']")).click();
		
		WebElement pic = driver.findElement(By.xpath("//div[contains(@class,'x1ey2m1c x78zum5 xdt5ytf xds687c x17qophe xl56j7k x10l6tqk x13vifvy')]"));
		
		
		
	}

}
