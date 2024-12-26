package pratice.testng;

import java.lang.reflect.Method;

import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RepoterLogProgram {
	
	@Test
	public void demoTest(Method mt) {
		Reporter.log(mt.getName());
		
		Reporter.log("Test2", true);
		Reporter.log("Test3", true);
		Reporter.log("Test4", true);
		Reporter.log("Test5", true);
		
		
	}

}
