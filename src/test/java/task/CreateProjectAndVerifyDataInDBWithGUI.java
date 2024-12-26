package task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws InterruptedException, SQLException {
		
		String projectId = "qsp_016";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("http://106.51.90.215:8084/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		
		driver.findElement(By.name("projectName")).sendKeys(projectId);
		
		driver.findElement(By.name("createdBy")).sendKeys("Sai");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='modal-footer']/input[@class='btn btn-success']")).submit();
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		
		if(connection != null)
			System.out.println("connected to database ");
		else
			System.out.println("Not connected to database");
		
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("Select * from project");
		
		String actProjectName = null;
		while(result.next()) {
			actProjectName = result.getString(4);					
		}
		if(projectId.equals(actProjectName)) {
			System.out.println("Data added into database" + actProjectName);
		}else {
			System.out.println("Data not added into database" + actProjectName);
			
		}
		connection.close();
		
	}

}
