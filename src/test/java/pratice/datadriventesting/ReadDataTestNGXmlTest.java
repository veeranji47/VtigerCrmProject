package pratice.datadriventesting;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataTestNGXmlTest {
	
	@Test
	public void readData(XmlTest test) {
		System.out.println(test.getParameter("browser"));
		System.out.println(test.getParameter("url"));
		System.out.println(test.getParameter("username"));
		System.out.println(test.getParameter("password"));
		System.out.println(test.getParameter("timeouts"));
	}

}
