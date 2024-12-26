package pratice.datadriventesting;

import java.util.Random;

public class RandomGen {
	public StringBuilder randomAlphaNum() {
		int n = 15;
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789abcdefghijklmnopqrstuvwxyz";
		
		StringBuilder sb = new StringBuilder(n);
		for(int i = 0; i < n; i++) {
			int index = (int) (alphaNumeric.length()* Math.random());
			 sb.append(alphaNumeric.charAt(index));
		}
		
		return sb;
	}
	public int randomNum() {
		Random random = new Random();
		return random.nextInt(1000);
		
	}
	public static void main(String[] args) {
		
		RandomGen rg = new RandomGen();
	//	System.out.println(rg.randomAlphaNum());
		System.out.println(rg.randomNum());
	}

}
