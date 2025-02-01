package pratice.testng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FetchingDataRankWise {
	
	@Test
	public void rankData() {
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/ASHOK%20KUMAR/Downloads/Webtable%20(1).html");
		
		Map<Integer, String[]> map1 = new HashMap();
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[1]/tbody/tr[position()>2]"));
		for(WebElement row : tableRows) {
			
			String country = row.findElement(By.xpath("./td[position()=2]")).getText();
			String population = row.findElement(By.xpath("./td[position()=3]")).getText();
			int rank = Integer.parseInt(row.findElement(By.xpath("./td[position()=4]")).getText());
			
			map1.put(rank, new String[] {country,population});
		}
		
		Map<Integer, String> map2 = new HashMap();
		
		List<WebElement> sectableRows = driver.findElements(By.xpath("//table[2]/tbody/tr[position()>2]"));
		for(WebElement row : sectableRows) {
			
			int rank = Integer.parseInt(row.findElement(By.xpath("./td[position()=1]")).getText());
			String avgSal = row.findElement(By.xpath("./td[position()=3]")).getText();
			map2.put(rank, avgSal);	
		}
		
//		ArrayList<Integer> sortRank = new ArrayList(map1.entrySet());
//		Collections.sort(sortRank);
//		
//		LinkedHashMap<Integer, String[]> result = new LinkedHashMap();
//		
//		for(int rank :map2.keySet()) {
//			if(map1.containsKey(rank)) {
//				String[] value1 = map1.get(rank);
//				String sal = map2.get(rank);
//				
//				System.out.println(rank + "\t"+ value1[0]+"\t"+value1[1]+ "\t"+sal);
//			}
//		}
		List<Integer> sortedRank = new LinkedList(map1.keySet());
		Collections.sort(sortedRank);
		
		System.out.printf("%-5s %-15s %-10s %-5s\n", "Rank", "Country", "Population", "Avg Salary");
        for (int rank : sortedRank) {
            if (map2.containsKey(rank)) {
                String[] table1Values = map1.get(rank);
                String avgSalary = map2.get(rank);
                System.out.printf("%-5d %-15s %-10s %-10s\n", rank, table1Values[0], table1Values[1], avgSalary);
            }
        }
		
		System.out.println(map1);
		System.out.println(map2);
		
	}

}
