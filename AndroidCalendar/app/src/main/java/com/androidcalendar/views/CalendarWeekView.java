package com.androidcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import com.androidcalendar.R;
import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.objects.CalendarWeek;
import com.androidcalendar.utils.Utils;


public class CalendarWeekView extends FrameLayout {

    private GridLayout mGridLayout;
    private ViewGroup mLayoutDays;

    public CalendarWeekView(Context context) {
        super(context);
        init();
    }

    public CalendarWeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarWeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_calendar_month, this);
        mGridLayout = (GridLayout) findViewById(R.id.view_calendar_month_grid);
        mLayoutDays = (ViewGroup) findViewById(R.id.view_calendar_month_layout_days);
    }

    public void buildView(CalendarWeek calendarWeek) {
        buildDaysLayout();
        buildGridView(calendarWeek);
    }

    private void buildDaysLayout() {
        String[] days;
        days = getResources().getStringArray(R.array.days_sunday_array);

        for (int i = 0; i < mLayoutDays.getChildCount(); i++) {
            TextView textView = (TextView) mLayoutDays.getChildAt(i);
            textView.setText(days[i]);
        }
    }

    private void buildGridView(CalendarWeek calendarWeek) {
        int row = CalendarWeek.NUMBER_OF_WEEKS_IN_WEEK;
        int col = CalendarWeek.NUMBER_OF_DAYS_IN_WEEK;
        mGridLayout.setRowCount(row);
        mGridLayout.setColumnCount(col);

        int screenWidth = Utils.getScreenWidth(getContext());
        int width = screenWidth / col;

        for (CalendarDate day : calendarWeek.getDays()) {
            CalendarDayView dayView = new CalendarDayView(getContext(), day);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = width;
            params.height = LayoutParams.WRAP_CONTENT;

            dayView.setLayoutParams(params);
            dayView.setTextDay("" + day.getDay());
            decorateDayView(dayView, day, calendarWeek.getMonth());
            mGridLayout.addView(dayView);
        }
    }

    protected void decorateDayView(CalendarDayView dayView, CalendarDate day, int month) {

//        if (day.getMonth() != month) {
//            dayView.setOtherMothTextColor();
//        } else {
//            dayView.setThisMothTextColor();
//        }

        dayView.setThisMothTextColor();

        if (day.isToday()) {
            dayView.setPurpleSolidOvalBackground();
        }
    }
}
