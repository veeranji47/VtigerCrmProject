package pratice.testng;


import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;


import org.testng.annotations.Test;

class OccuranceInSortedOrder {
	
	@Test
	public void test() {
		String name = "hello welcome";
		String[] s = name.split(" ");
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for(int i=0;i<s.length;i++) {
			String str = s[i];
		
			for(int j=0;j<str.length();j++) {
				if(map.containsKey(str.charAt(j))) {
					map.put(str.charAt(i), map.get(str.charAt(j))+1);
				}else {
					map.put(str.charAt(j), 1);
				}
			}
		}
		
		List<Entry<Character, Integer>> list = new LinkedList(map.entrySet());
		
		list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
		System.out.println(list);
		
//		for(Entry<Character, Integer> hash:list) {
//			System.out.println(hash.getKey() + " ---> "+ hash.getValue());
//		}
	}
	@Test
	public void convertUppercase() {
//		String str = "Welcome to tp office", op="";
//		String[] s = str.split(" ");
//		for(int i=0;i<s.length;i++) {
//			String temp = s[i];
//			op = op+Character.toUpperCase(temp.charAt(0))+temp.substring(1)+" ";
//		}
//		System.out.println(op.trim());
		String str = "Welcome to tp office", op = "";
		String[] s = str.split(" ");
		for (int i = 0; i < s.length; i++) {
		    String temp = s[i];
		    op = op + Character.toUpperCase(temp.charAt(0)) + temp.substring(1) + " ";
		}
		System.out.println(op.trim());

	}

}
