package pratice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PriorityProgram {
	@Test(priority = -1)
	public void createCustomerTest() {
//		Assert.fail();
		System.out.println("Cutomer created :) ");
	}
	
	@Test(priority = 1)
	public void modifyCustomerTest() {
		Assert.fail();
		System.out.println("Cutomer modified :) ");
	}
	@Test(priority = 2)
	public void deleteCustomerTest() {
		System.out.println("Cutomer deleted :) ");
	}

}
