package com.genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	/**
	 * This method gets the random number between the range of 100 to 1000
	 * @return integer
	 */
	
	public int getRandomNum() {
		Random random = new Random();
		return random.nextInt(100, 1000);
	}
	
	/**
	 * This method fetches the Random alpha numeric number
	 * @return String
	 */
	public String getRandomAlphaNumeric() {
		int n = 10;
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
		StringBuilder builder = new StringBuilder();
		
		Random random = new Random();
		for(int i = 0; i <= n; i++) {
			int character = random.nextInt(data.length());
			builder.append(data.charAt(character));
		}
		return builder.toString();
	}
	
	/**
	 * This method gets the current date YYYY-MM-DD these format
	 * @return String
	 * 
	 */
	public String getCurrentDateYYYYMMDD() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = sdf.format(date);
		return pdate;
	}
	
	/**
	 * This method fetch required date YYYY-MM-DD format
	 * @param days
	 * @return String
	 */
	public String getRequiredDate(int days) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		// Calendar.getInstance(): This retrieves the current date and time.
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		String date = sdf.format(calendar.getTime());
		return date;
	}
	
	public String getDateAndTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String date = sdf.format(dt);
		return date;
	}
	public static void main(String[] args) {
		JavaUtility ju = new JavaUtility();
		System.out.println(ju.getRequiredDate(20));
		System.out.print(ju.getDateAndTime());
	}
	 
}
