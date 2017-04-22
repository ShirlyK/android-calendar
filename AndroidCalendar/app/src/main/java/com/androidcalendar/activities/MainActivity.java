package com.androidcalendar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.androidcalendar.R;
import com.androidcalendar.interfaces.OnDateSelectedListener;
import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.views.CustomCalendarView;


public class MainActivity extends AppCompatActivity implements OnDateSelectedListener {

    private TextView mTextDay;
    private TextView mTextDayOfWeek;
    private CustomCalendarView mCustomCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextDay = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        mTextDayOfWeek = (TextView) findViewById(R.id.activity_main_text_day_of_week);
        mCustomCalendar = (CustomCalendarView) findViewById(R.id.activity_main_view_custom_calendar);
        mCustomCalendar.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(CalendarDate date) {
        mTextDay.setText(date.dayToString());
        mTextDayOfWeek.setText(date.dayOfWeekToStringName());
    }
}
