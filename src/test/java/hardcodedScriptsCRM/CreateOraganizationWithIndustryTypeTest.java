package hardcodedScriptsCRM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOraganizationWithIndustryTypeTest {

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
	
		driver.findElement(By.xpath("//a[.='Organizations' and contains(@href,'action=index')]")).click();
		
		String orgPageTitle = driver.getTitle();
		if(orgPageTitle.contains("Organization")) {
			System.out.println("Organization Page displayed...");
		}else {
			System.out.println("Organization page not displayed...(");
		}
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		String createOrgHeader = driver.findElement(By.className("lvtHeaderText")).getText();
		if(createOrgHeader.contains("Creating")) {
			System.out.println("Creating new organization Page displayed...");
		}else {
			System.out.println("Creating new organization page not displayed...(");
		}
		
		String orgName = wb.getSheet("org").getRow(1).getCell(2).toString()+rg.randomNum();
		String industryText = wb.getSheet("org").getRow(4).getCell(3).toString();
		String typeText = wb.getSheet("org").getRow(4).getCell(4).toString();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement industry = driver.findElement(By.name("industry"));
		Select selectIndu = new Select(industry);
		selectIndu.selectByVisibleText(industryText);
		WebElement type = driver.findElement(By.name("accounttype"));
		Select selectType = new Select(type);
		selectType.selectByVisibleText(typeText);
		
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();
		
		//verify the header
		String title = driver.findElement(By.className("dvHeaderText")).getText();
		String industryVerify = driver.findElement(By.id("dtlview_Industry")).getText();
		String typeVerify = driver.findElement(By.id("dtlview_Type")).getText();
		
		
		if(industryVerify.contains(industryText)) {
			wb.getSheet("org").getRow(4).createCell(5).setCellValue("PASS");
			System.out.println("Industry information verified :)");
		}else {
			wb.getSheet("org").getRow(4).createCell(5).setCellValue("FAIL");
			System.out.println("Industry verify  failed :( ");
		}
		if(typeVerify.contains(typeText)) {
			wb.getSheet("org").getRow(4).createCell(5).setCellValue("PASS");
			System.out.println("Type information verified :)");
		}else {
			wb.getSheet("org").getRow(4).createCell(5).setCellValue("FAIL");
			System.out.println("type verify  failed :( ");
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
