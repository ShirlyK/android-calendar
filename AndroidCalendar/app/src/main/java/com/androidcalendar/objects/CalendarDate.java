package com.androidcalendar.objects;


import com.androidcalendar.utils.DateUtils;

import java.util.Calendar;


public class CalendarDate {

    private int mDay;
    private int mMonth;
    private int mYear;

    /**
     * Constructor
     *
     * @param day   is the number of day in the month.
     * @param month value is 0-based: 0 for January, 11 for December.
     * @param year
     */
    CalendarDate(int day, int month, int year) {
        mDay = day;
        mMonth = month;
        mYear = year;
    }

    /**
     * Constructor
     *
     * @param calendar is a {@link java.util.Calendar} object.
     */
    public CalendarDate(Calendar calendar) {
        this(calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR));
    }

    /**
     * Constructor
     * This is a copy constructor.
     *
     * @param other is another instance of CalendarDate.
     */
    public CalendarDate(CalendarDate other) {
        this(other.getDay(),
                other.getMonth(),
                other.getYear());
    }

    /**
     * Return the value representation of the day of month in this calendar.
     *
     * @return day of month value
     */
    public int getDay() {
        return mDay;
    }

    /**
     * Return the value representation of the month in this calendar.
     * The month value is 0-based: 0 for January, 11 for December.
     *
     * @return month value
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * Return the value representation of the year in this calendar.
     *
     * @return year value
     */
    public int getYear() {
        return mYear;
    }

    /**
     * Get this calendar object in a {@link java.util.Calendar} format.
     * The time of this calendar is set to 00:00:00 with 0 milliseconds
     *
     * @return this calendar as {@link java.util.Calendar}.
     */
    public Calendar getCalender() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(mYear, mMonth, mDay);
        return calendar;
    }

    /**
     * Returns this Calendar time value in milliseconds.
     * The hour time of this calendar is set to 00:00:00 with 0 milliseconds
     *
     * @return the current time as UTC milliseconds from the epoch.
     */
    public long getMillis() {
        return getCalender().getTimeInMillis();
    }


    public int getDayOfWeek() {
        return getCalender().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns if this date of this calendar is the same date as today.
     *
     * @return true if the calendar represent the day of today.
     */
    public boolean isToday() {
        Calendar today = Calendar.getInstance();

        return mYear == today.get(Calendar.YEAR) &&
                mMonth == today.get(Calendar.MONTH) &&
                mDay == today.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isDateEqual(CalendarDate other) {
        return mYear == other.getYear() &&
                mMonth == other.getMonth() &&
                mDay == other.getDay();
    }

    /**
     * Adds or subtracts the specified amount of days to the calendar.
     *
     * @param value the amount of days added (set a negative value for subtraction).
     */
    public void addDays(int value) {
        Calendar calendar = getCalender();
        calendar.add(Calendar.DATE, value);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
    }


    /**
     * Return a string representation of this calendar.
     *
     * @return string in a format of dd/mm/yyy
     */
    @Override
    public String toString() {
        return dayToString() + "/" + monthToString() + "/" + yearToString();
    }

    /**
     * Return a string representation of the day of month in this calendar.
     *
     * @return String of the day of month number in a format of dd
     */
    public String dayToString() {
        if (mDay < 10) {
            return "0" + mDay;
        } else {
            return "" + mDay;
        }
    }

    /**
     * Return a string representation of the month in this calendar.
     *
     * @return String of the month number in a format of mm
     */
    public String monthToString() {
        if ((mMonth + 1) < 10) {
            return "0" + (mMonth + 1);
        } else {
            return "" + (mMonth + 1);
        }
    }

    /**
     * Return a string representation of the year in this calendar.
     *
     * @return String of the year number in a format of yyyy.
     */
    public String yearToString() {
        return String.valueOf(mYear);
    }


    /**
     * Return a string representation of the month name in this calendar.
     *
     * @return the month name like it appears in the Julian and Gregorian calendars as a string.
     */
    public String monthToStringName() {
        return DateUtils.monthToString(mMonth);
    }

    /**
     * Return a string representation of the day of week name in this calendar.
     *
     * @return the day of week name like it appears in the Julian and Gregorian calendars as a string.
     */
    public String dayOfWeekToStringName() {
        return DateUtils.dayOfWeekToString(getCalender().get(Calendar.DAY_OF_WEEK));
    }

}
