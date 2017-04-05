package com.androidcalendar.objects;


import com.androidcalendar.utils.Utils;

import java.util.Calendar;


public class CalendarDate {

    private int mDay;
    private int mMonth;
    private int mYear;

    public CalendarDate(int day, int month, int year) {
        mDay = day;
        mMonth = month;
        mYear = year;
    }

    public CalendarDate(Calendar calendar) {
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
    }

    public CalendarDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
    }

    public int getDay() {
        return mDay;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public int getDayOfWeek() {
        return getCalender().get(Calendar.DAY_OF_WEEK);
    }

    public Calendar getCalender() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(mYear, mMonth, mDay);
        return calendar;
    }

    public long getMillis() {
        return getCalender().getTimeInMillis();
    }

    public boolean isToday() {
        Calendar today = Calendar.getInstance();

        return mYear == today.get(Calendar.YEAR) &&
                mMonth == today.get(Calendar.MONTH) &&
                mDay == today.get(Calendar.DAY_OF_MONTH);
    }

    public void addDays(int value) {
        Calendar calendar = getCalender();
        calendar.add(Calendar.DATE, value);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
    }

    /**
     * toString
     */

    @Override
    public String toString() {
        return dayToString() + "/" + monthToString() + "/" + yearToString();
    }

    public String dayToString() {
        if (mDay < 10) {
            return "0" + mDay;
        } else {
            return "" + mDay;
        }
    }

    public String monthToString() {
        if ((mMonth + 1) < 10) {
            return "0" + (mMonth + 1);
        } else {
            return "" + (mMonth + 1);
        }
    }

    public String monthToStringName() {
        return Utils.monthToString(mMonth);
    }

    public String yearToString() {
        return String.valueOf(mYear);
    }

}
