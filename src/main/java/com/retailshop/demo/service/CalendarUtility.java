package com.retailshop.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;


public class CalendarUtility {

	private static CalendarUtility calendarUtility;
	private CalendarUtility(){
		
	}
	
	public static CalendarUtility getInstance(){
		if(calendarUtility == null)
			calendarUtility = new CalendarUtility();
		return calendarUtility;
	}

    public int noOfWeekendsBetween(LocalDate startInclusive, int noOfDays) {
        int noOfWeekends = 0;
        for(int i = 0; i< noOfDays; i++) {
            if(isWeekend(startInclusive.plusDays(i))) {
                noOfWeekends ++;
            }
        }
        return noOfWeekends;
    }

    public int noOfHolidaysBetween(LocalDate startInclusive, int noOfDays) {
        int noOfHolidays = 0;
        for(int i = 0; i< noOfDays; i++) {
            if(isHoliday(startInclusive.plusDays(i))) {
                noOfHolidays ++ ;
            }
        }
        return noOfHolidays;
    }

    private boolean isHoliday(LocalDate startInclusive) {
        return startInclusive.equals(getIndependenceHoliday(startInclusive.getYear())) || startInclusive.equals(getLabourDay(startInclusive.getYear()));
    }

    private LocalDate getIndependenceHoliday(int year) {
        LocalDate julyFourth = LocalDate.of(year, Month.JULY, 4);
        if(isSaturday(julyFourth)) {
            return julyFourth.minusDays(1);
        } else if (isSunday(julyFourth)) {
            return julyFourth.plusDays(1);
        } else return julyFourth;
    }

    private LocalDate getLabourDay(int year) {
        return LocalDate.of(year, Month.SEPTEMBER,1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    private boolean isWeekend(final LocalDate localDate) {
        return isSaturday(localDate) || isSunday(localDate);
    }

    private boolean isSaturday(final LocalDate localDate) {
        final DayOfWeek dow = localDate.getDayOfWeek();
        return dow == DayOfWeek.SATURDAY;
    }

    private boolean isSunday(final LocalDate localDate) {
        final DayOfWeek dow = localDate.getDayOfWeek();
        return dow == DayOfWeek.SUNDAY;
    }
}
