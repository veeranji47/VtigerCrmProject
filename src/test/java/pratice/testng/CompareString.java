package pratice.testng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class CompareString {
	
	@Test
	public void compareString() {
		List<String> list = Arrays.asList("Hi","Hello","Bye");
		List<String> list2 = Arrays.asList("Hi","Welcome","Bye");
		
		list.retainAll(list2);
//		ArrayList result = new ArrayList();
//		
//		for(String s1 : list2) {
//			for(String s2 : list ) {
//				if(s1.equals(s2)) {
//					result.add(s1);
//				}
//			}
//		}
		System.out.println(list2);
	}

}
