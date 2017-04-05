package com.androidcalendar.views;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidcalendar.R;
import com.androidcalendar.objects.CalendarDate;


public class CalendarDayView extends LinearLayout implements View.OnClickListener {

    public interface OnCalendarDayViewClickListener {
        void OnCalendarDayViewClick(CalendarDate day);
    }

    public static final String ACTION_CALENDAR_DAY_VIEW_CLICK = "action_calendar_day_view_click";
    public static final String EXTRA_CALENDAR_DAY_VIEW_DATA = "extra_calendar_day_view_data";
    private CalendarDate mCalendarDate;
    private TextView mTextDay;
    private View mLayoutBackground;

    public CalendarDayView(Context context, CalendarDate calendarDate) {
        super(context);
        mCalendarDate = calendarDate;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_calendar_day, this);
        setOnClickListener(this);
        mTextDay = (TextView) findViewById(R.id.view_calendar_day_text);
        mLayoutBackground = findViewById(R.id.view_calendar_day_layout_background);
    }

    public void setTextDay(String textDay) {
        mTextDay.setText(textDay);
    }

    public void setThisMothTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    public void setOtherMothTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.grey));
    }

    public void setPurpleSolidOvalBackground() {
        mLayoutBackground.setBackgroundResource(R.drawable.oval_purple_solid);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ACTION_CALENDAR_DAY_VIEW_CLICK);
        intent.putExtra(EXTRA_CALENDAR_DAY_VIEW_DATA, mCalendarDate.getMillis());
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }

}
