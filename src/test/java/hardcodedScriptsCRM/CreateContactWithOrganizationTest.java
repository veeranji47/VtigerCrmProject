package hardcodedScriptsCRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("./commonData.properties");
		FileInputStream fis2 = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis2);
		
		RandomGen rg = new RandomGen();
		Properties property = new Properties();
		property.load(fis);
		
		String Browser = property.getProperty("browser");
		String url = property.getProperty("url");
		String un = property.getProperty("username");
		String psw = property.getProperty("password");
		String timeout = property.getProperty("timeouts");
		int time = Integer.parseInt(timeout);
		
		
		WebDriver driver = null;
		if(Browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(Browser.equals("Edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Invalid browser :(");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(url);
		
		String welPageTitle = driver.getTitle();
		if(welPageTitle.contains("Commercial")) {
			System.out.println("Login Page displayed...");
		}else {
			System.out.println("Login page not displayed...(");
		}
		
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(psw);
		driver.findElement(By.id("submitButton")).click();
		
		String homePageTitle = driver.getTitle();
		if(homePageTitle.contains("Home")) {
			System.out.println("Home Page displayed...");
		}else {
			System.out.println("Home page not displayed...(");
		}
	
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		
		String contactPageTitle = driver.getTitle();
		if(contactPageTitle.contains("Contacts")) {
			System.out.println("Contacts Page displayed...");
		}else {
			System.out.println("Contacts page not displayed...(");
		}
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		String createContactHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createContactHeader.contains("Creating")) {
			System.out.println("Creating new Contact Page displayed...");
		}else {
			System.out.println("Creating new Contact page not displayed...(");
		}
		
		String contactLastName = wb.getSheet("contact").getRow(7).getCell(3).toString()+rg.randomNum();
		
		String parentWindowId = driver.getWindowHandle();
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//table[@class='small']/descendant::tr[5]/descendant::img[contains(@src,'images/select.gif')]")).click();
		Set<String> ids = driver.getWindowHandles();
		for(String childIds : ids) {
			if(!parentWindowId.equals(childIds)) {
				driver.switchTo().window(childIds);
				break;
			}
		}
		String orgName = wb.getSheet("contact").getRow(7).getCell(2).toString();
		driver.findElement(By.xpath("//a[contains(.,'"+orgName+"')]")).click();
		
		driver.switchTo().window(parentWindowId);
		
		
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();
		
		//verify the contact with Organization Name
		String orgNameVerify = driver.findElement(By.xpath("//td[text()='Organization Name']/following::a[contains(.,'"+orgName+"')]")).getText();
		
		if(orgNameVerify.contains(orgName)) {
			wb.getSheet("org").getRow(7).createCell(4).setCellValue("PASS");
			System.out.println(orgNameVerify+" Testcase verified passed :)");
		}else {
			wb.getSheet("org").getRow(1).createCell(4).setCellValue("FAIL");
			System.out.println(orgNameVerify+" Testcase failed :( ");
		}
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		
		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		actions.moveToElement(signOut).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.close();
		
	}

}
