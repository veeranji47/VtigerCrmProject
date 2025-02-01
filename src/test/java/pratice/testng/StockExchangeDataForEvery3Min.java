package pratice.testng;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StockExchangeDataForEvery3Min {
	
	@Test
	public void exchangeData() throws Exception {
		WebDriver driver = new ChromeDriver();

        // Open the stock exchange webpage
        driver.get("https://example.com/stock-exchange"); // Replace with the actual URL
        driver.manage().window().maximize();

        // Loop to fetch data every 3 minutes
        for (int i = 0; i < 5; i++) { // Run 5 iterations as an example (15 minutes)
            System.out.println("Fetching stock data (iteration " + (i + 1) + ")...");

            // Locate the stock table (adjust the locator to match the actual page)
            WebElement stockTable = driver.findElement(By.id("stock-table")); // Replace 'stock-table' with actual table ID or CSS selector
            List<WebElement> rows = stockTable.findElements(By.tagName("tr"));

            System.out.println("Stock Data:");
            for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) { // Skip header row
                WebElement row = rows.get(rowIndex);

                // Fetch data from the row (adjust column indices as per the table structure)
                String stockName = row.findElement(By.xpath("td[1]")).getText(); // Column 1: Stock name
                String stockPrice = row.findElement(By.xpath("td[2]")).getText(); // Column 2: Price
                WebElement changeCell = row.findElement(By.xpath("td[3]")); // Column 3: Change with arrow

                // Determine if the stock is gaining or losing
                String arrowClass = changeCell.findElement(By.tagName("span")).getAttribute("class");
                String status = arrowClass.contains("arrow-up") ? "Gaining" : "Losing";

                // Print stock details
                System.out.println("Stock: " + stockName + ", Price: " + stockPrice + ", Status: " + status);
            }

            System.out.println("Waiting for 3 minutes before the next fetch...");
            Thread.sleep(180000); // Wait for 3 minutes (180,000 milliseconds)
        }

        // Close the browser
        driver.quit();

	}

}
