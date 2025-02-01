package pratice.testng;

public class ConvertFirstLetterUpperCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Welcome to tp office", op="";
		String[] s = str.split(" ");
		for(int i=0;i<s.length;i++) {
			String temp = s[i];
			op = op+Character.toUpperCase(temp.charAt(0))+" ";
		}
		System.out.println(op);

	}

}
