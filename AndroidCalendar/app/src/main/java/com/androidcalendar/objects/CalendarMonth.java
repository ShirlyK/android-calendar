package com.androidcalendar.objects;

import com.androidcalendar.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarMonth {

    public static final int NUMBER_OF_WEEKS_IN_MONTH = 6;
    public static final int NUMBER_OF_DAYS_IN_WEEK = 7;
    private int mMonth;
    private int mYear;
    private List<CalendarDate> mDays;

    public CalendarMonth(Calendar calendar) {
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        createMonthDays();
    }

    public CalendarMonth(CalendarMonth other, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(other.getYear(), other.getMonth(), 1);
        calendar.add(Calendar.MONTH, value);

        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        createMonthDays();
    }

    private void createMonthDays() {
        CalendarDate date = new CalendarDate(1, mMonth, mYear);
        int dayOfWeek = date.getDayOfWeek();
        date.addDays(1 - dayOfWeek);

        mDays = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DAYS_IN_WEEK * NUMBER_OF_WEEKS_IN_MONTH; i++) {
            CalendarDate day = new CalendarDate(date.getDay(), date.getMonth(), date.getYear());
            mDays.add(day);
            date.addDays(1);
        }
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public List<CalendarDate> getDays() {
        return mDays;
    }

    @Override
    public String toString() {
        return DateUtils.monthToString(mMonth) + "  " + mYear;
    }
}
