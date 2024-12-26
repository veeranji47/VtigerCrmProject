package pratice.testng;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pratice.basetest.BaseClass;

public class Example extends BaseClass {
	@Test
	public void test1() {
		Reporter.log("Hi", true);
		Assert.fail();
	}
	@Test
	public void test2() {
		Reporter.log("Bye", true);
	}
}
