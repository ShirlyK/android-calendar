package com.androidcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import com.androidcalendar.R;
import com.androidcalendar.utils.Utils;
import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.objects.CalendarMonth;


public class CalendarMonthView extends FrameLayout {

    private GridLayout mGridLayout;
    private ViewGroup mLayoutDays;

    public CalendarMonthView(Context context) {
        super(context);
        init();
    }

    public CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarMonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_calendar_month, this);
        mGridLayout = (GridLayout) findViewById(R.id.view_calendar_month_grid);
        mLayoutDays = (ViewGroup) findViewById(R.id.view_calendar_month_layout_days);
    }

    public void buildView(CalendarMonth calendarMonth) {
        buildDaysLayout(calendarMonth);
        buildGridView(calendarMonth);
    }

    private void buildDaysLayout(CalendarMonth calendarMonth) {
        String[] days;
        days = getResources().getStringArray(R.array.days_sunday_array);

        for (int i = 0; i < mLayoutDays.getChildCount(); i++) {
            TextView textView = (TextView) mLayoutDays.getChildAt(i);
            textView.setText(days[i]);
        }
    }

    private void buildGridView(CalendarMonth calendarMonth) {
        int row = CalendarMonth.NUMBER_OF_WEEKS_IN_MONTH;
        int col = CalendarMonth.NUMBER_OF_DAYS_IN_WEEK;
        mGridLayout.setRowCount(row);
        mGridLayout.setColumnCount(col);

        int screenWidth = Utils.getScreenWidth(getContext());
        int width = screenWidth / col;

        for (CalendarDate day : calendarMonth.getDays()) {
            CalendarDayView dayView = new CalendarDayView(getContext(), day);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width;
            params.height = LayoutParams.WRAP_CONTENT;

            dayView.setLayoutParams(params);
            dayView.setTextDay("" + day.getDay());
            decorateDayView(dayView, day, calendarMonth.getMonth());
            mGridLayout.addView(dayView);
        }
    }

    protected void decorateDayView(CalendarDayView dayView, CalendarDate day, int month) {
        if (day.getMonth() != month) {
            dayView.setOtherMothTextColor();
        } else {
            dayView.setThisMothTextColor();
        }

        if (day.isToday()) {
            dayView.setPurpleSolidOvalBackground();
        }
    }
}
