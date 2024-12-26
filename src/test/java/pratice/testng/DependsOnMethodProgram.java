package pratice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependsOnMethodProgram {
	@Test
	public void createCustomerTest() {
//		Assert.fail();
		System.out.println("Cutomer created :) ");
	}
	
	@Test(dependsOnMethods = "createCustomerTest")
	public void modifyCustomerTest() {
		Assert.fail();
		System.out.println("Cutomer modified :) ");
	}
	@Test(dependsOnMethods = {"createCustomerTest", "modifyCustomerTest"})
	public void deleteCustomerTest() {
		System.out.println("Cutomer deleted :) ");
	}

}
