package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DateCalculator {
	public static boolean isWithinRange(String test, String before) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime testDate = LocalDateTime.parse(test.replace("Z", ""),formatter);
		LocalDateTime startDate = LocalDateTime.parse(before.replace("Z", ""),formatter);
		LocalDateTime endDate = startDate.plus(1,ChronoUnit.HOURS);
		return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
	}
	
	public static int returnHour(String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(str.replace("Z", ""),formatter);
		int hour = date.getHour();
		return hour;
	}
	
	public static String returnDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime testDate = LocalDateTime.parse(date.replace("Z", ""),formatter);
		testDate.withMinute(0);
		testDate.withSecond(0);
		
		String s = testDate.format(formatter);
		
		String formattedDateTime = s.substring(0, s.length() -6);
		return formattedDateTime;
	}
}
