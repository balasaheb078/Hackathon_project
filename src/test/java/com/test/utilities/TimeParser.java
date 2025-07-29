package com.test.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.test.resources.Activity;

public class TimeParser {
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h[:mm]a");

    public static LocalDateTime parseDateTime(String datePart, String timePart, int defaultYear) {
    	//Parses date from string
        try {
        	String fullDate = datePart.trim() + " " + defaultYear;
            LocalDate date = LocalDate.parse(fullDate, dateFormatter);
            LocalTime time = LocalTime.parse(timePart.replaceAll("\\s", "").toUpperCase(), timeFormatter);
            return LocalDateTime.of(date, time);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing: " + datePart + " " + timePart);
            return null;
        }
    }
    
    public static boolean isHappeningOnWeekend(Activity activity) {
    	//Checks if the date is on weekend
        LocalDate start = activity.getStartDateTime().toLocalDate();
        LocalDate end = activity.getEndDateTime() != null
            ? activity.getEndDateTime().toLocalDate()
            : start;

        while (!start.isAfter(end)) {
            DayOfWeek day = start.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                return true;
            }
            start = start.plusDays(1);
        }
        return false;
    }
}
