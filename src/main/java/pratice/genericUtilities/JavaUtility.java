package pratice.genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	Date date;
	SimpleDateFormat sdf;
	Random random;
	
	public void getCurrentDateFormatYYYYMMDD() {
		date = new Date();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sdf.format(date);
		System.out.println(curDate);
	}
	
	public String getRequiredDate(int days) {

		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return sdf.format(cal.getTime());	
	}
	
	public int getRandomNum(int startNum, int endNum) {
		random = new Random();
		return random.nextInt(startNum, endNum);
	}
	
	public String getRandomAlphaNumericNum(int length) {
		String text = "ABCDEFGHIJKLMONPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123467890";
		random = new Random();
		StringBuilder sb = new StringBuilder(length);
		for(int i = 0; i < length; i++) {
			int size = random.nextInt(text.length());
			sb.append(text.charAt(size));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		JavaUtility ju = new JavaUtility();
//		ju.getCurrentDateFormatYYYYMMDD();
//		ju.getRequiredDate(10);
		System.out.println(ju.getRandomNum(1000, 9999));
		System.out.println(ju.getRandomAlphaNumericNum(25));
	}

}
