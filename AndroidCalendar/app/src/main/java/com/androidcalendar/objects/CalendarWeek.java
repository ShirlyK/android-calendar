package com.androidcalendar.objects;

import android.util.Log;

import com.androidcalendar.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarWeek {

    public static final int NUMBER_OF_WEEKS_IN_WEEK = 1;
    public static final int NUMBER_OF_DAYS_IN_WEEK = 7;
    private int mMonth;
    private int mYear;
    private int mWeekOfMonth;
    private int mWeekOfYear;
    private List<CalendarDate> mDays;
    private int mCurrentDayOfMonth;

    public CalendarWeek(Calendar calendar) {
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mWeekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        mWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        mCurrentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        createWeekDays();
    }

    public CalendarWeek(CalendarWeek other, int value) {
        Calendar calendar = Calendar.getInstance();

        // set calendarWeek
        calendar.set(other.mYear, other.mMonth, other.mCurrentDayOfMonth);

        // add number of weeks to current calendar
        calendar.add(Calendar.WEEK_OF_YEAR, value);


        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mWeekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        mWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        mCurrentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        createWeekDays();
    }

    private void createWeekDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mCurrentDayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, 1 - dayOfWeek);

        Log.d("TEST", "Day of week :" + dayOfWeek + "    DAte :" + calendar.getTime().toString());

        CalendarDate date = new CalendarDate(calendar.get(Calendar.DAY_OF_MONTH), mMonth, mYear);

        mDays = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DAYS_IN_WEEK; i++) {
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

    public int getCurrentDayOfMonth() {
        return mCurrentDayOfMonth;
    }

    public List<CalendarDate> getDays() {
        return mDays;
    }

    @Override
    public String toString() {
        return Utils.monthToString(mMonth) + "  " + mYear;
    }
}
