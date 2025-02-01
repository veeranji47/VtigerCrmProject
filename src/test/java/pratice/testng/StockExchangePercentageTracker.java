package pratice.testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StockExchangePercentageTracker {
	
	@Test
	public void stackExchangePercentage() {
        WebDriver driver = new ChromeDriver();

        // Open the stock exchange webpage
        driver.get("https://example.com/stock-exchange"); // Replace with the actual URL of the stock exchange
        driver.manage().window().maximize();

        // Fetch data from the table
        WebElement stockTable = driver.findElement(By.id("stock-table")); // Replace with the actual table ID or locator
        List<WebElement> rows = stockTable.findElements(By.tagName("tr"));

        System.out.println("Fetching Stock Gains and Losses Data...");
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) { // Skip header row
            WebElement row = rows.get(rowIndex);

            // Fetch data from columns
            String stockName = row.findElement(By.xpath("td[1]")).getText(); // Column 1: Stock name
            String stockPrice = row.findElement(By.xpath("td[2]")).getText(); // Column 2: Current price
            WebElement changeCell = row.findElement(By.xpath("td[3]")); // Column 3: Change with percentage and arrow

            // Extract percentage and status (gain/loss)
            String percentageChange = changeCell.findElement(By.tagName("span")).getText(); // Percentage change, e.g., "+2.5%" or "-1.8%"
            String arrowClass = changeCell.findElement(By.tagName("span")).getAttribute("class"); // Arrow class to determine gain or loss
            String status = arrowClass.contains("arrow-up") ? "Gaining" : "Losing";

            // Print the stock details
            System.out.println("Stock: " + stockName + ", Price: " + stockPrice + ", Status: " + status + ", Change: " + percentageChange);
        }

        // Close the browser
        driver.quit();
	}

}
