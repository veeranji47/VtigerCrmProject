package pratice.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test(priority = -1)
	public void test1() {
		Assert.fail();
		System.out.println("Hi");
	}
	@Test
	public void test2() {
		System.out.println("Hello"); 
	}
	@Test(priority = 1)
	public void test3() { 
		System.out.println("bye"); 
	}

}
