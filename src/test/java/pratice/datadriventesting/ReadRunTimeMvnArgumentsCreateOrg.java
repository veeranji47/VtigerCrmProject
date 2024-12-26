package pratice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ReadRunTimeMvnArgumentsCreateOrg {

	@Test
	public void createOrg() throws Exception {

		// get the file into java representation object
		FileInputStream fis = new FileInputStream("./commonData.properties");
		FileInputStream fis2 = new FileInputStream("./src/test/resources/data.xlsx");

		RandomGen random = new RandomGen();
		StringBuilder randomNum = random.randomAlphaNum();

		Workbook wb = WorkbookFactory.create(fis2);
		String orgName = wb.getSheet("Condition").getRow(1).getCell(2).toString() + randomNum;
		
		String Browser = System.getProperty("browser");
		String URL = System.getProperty("url");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
//		String timeOuts = System.getProperty("Timeouts");
//		long time = (int) Long.parseLong(timeOuts);
		

		WebDriver driver = null;
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (Browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser :(");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.xpath("//a[.='Organizations' and contains(@href,'action=index')]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//table/tbody/tr[1]/td/div/input[@accesskey='S']")).click();

		// verify the header
		String title = driver.findElement(By.className("dvHeaderText")).getText();

		if (title.contains(orgName)) {
			wb.getSheet("Condition").getRow(1).createCell(4).setCellValue("PASS");
			System.out.println("Test case passed :)");
		} else {
			wb.getSheet("Condition").getRow(1).getCell(4).setCellValue("FAIL");
			System.out.println("Test case failed :( ");
		}

		Actions actions = new Actions(driver);
		WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'images/user.PNG')]"));
		actions.moveToElement(signOut).perform();

		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		driver.close();
		// convert the file readable format to write / editable format an excel file
		FileOutputStream fos = new FileOutputStream("./src/test/resources/data.xlsx");
		// save the modified data into an excel
		wb.write(fos);
		// close the excel file
		wb.close();

	}

}
