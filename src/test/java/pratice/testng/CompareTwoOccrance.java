package pratice.testng;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.graphbuilder.struc.LinkedList;

public class CompareTwoOccrance {
	@Test
	public void test() {
		String name = "hellooo welcomeeehh";
		String[] s = name.split(" ");
		String str = s[0], str2 = s[1];
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			} else {
				map.put(str.charAt(i), 1);
			}
		}

		LinkedHashMap<Character, Integer> map2 = new LinkedHashMap<Character, Integer>();
		for (int i = 0; i < str2.length(); i++) {
			if (map2.containsKey(str2.charAt(i))) {
				map2.put(str2.charAt(i), map2.get(str2.charAt(i)) + 1);
			} else {
				map2.put(str2.charAt(i), 1);
			}
		}

		//System.out.println(map + "\n" + map2);
		LinkedHashMap<Character, Integer> result = new LinkedHashMap();
		for(char key : map.keySet()) {
			int value1 = map.get(key);
			int value2 = map2.get(key);
			
			result.put(key, Math.max(value1, value2));
		}
		
		for(char key : map2.keySet()) {
			if(!result.containsKey(key)) {
				result.put(key, map2.get(key));
			}
		}
		List list = new ArrayList(result.entrySet()) ;
		list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
		System.out.println(list.get(0));
		//List list = new ArrayList(result.entrySet());
		
		
	}
}
