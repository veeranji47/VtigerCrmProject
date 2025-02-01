import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Download {
	@Test
	public void isDownload() {
//		String path = "D:\\veera tp";
//		Map<String, Object> map = new HashMap();
//		map.put("download.default_directory", path);
//		//map.put("profile.default_content_settings.popups", 0);
//		
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("path", path);
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoapps.qspiders.com/ui/download?sublist=0");
		driver.findElement(By.id("writeArea")).sendKeys("Hello");
		driver.findElement(By.id("downloadButton")).click();
		
	}

}
