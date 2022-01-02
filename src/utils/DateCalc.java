package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DateCalc {
	public static boolean isWithinRange(LocalDateTime testDate, LocalDateTime startDate, LocalDateTime endDate) {
		return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
	}
	
	
}
