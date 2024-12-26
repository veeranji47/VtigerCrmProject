package task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PresentData {

	public static void main(String[] args) {
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(dateObj);
		
		Calendar cal =sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 10);
		String reqDate = sdf.format(cal.getTime());
		
		System.out.println("Current date : " + currentDate);
		System.out.println("Required date : " + reqDate);
		
		
	}

}
