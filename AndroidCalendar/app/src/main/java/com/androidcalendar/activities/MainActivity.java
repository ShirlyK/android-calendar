package com.androidcalendar.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcalendar.R;
import com.androidcalendar.broadcast_receivers.DayViewClickReceiver;
import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.utils.Utils;
import com.androidcalendar.views.CalendarDayView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements CalendarDayView.OnCalendarDayViewClickListener {

    private TextView mTextMainDay;
    private TextView mTextMainMonth;
    private DayViewClickReceiver mDayViewClickReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // main view
        mTextMainDay = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        mTextMainMonth = (TextView) findViewById(R.id.activity_main_text_month);

        // day click receiver
        mDayViewClickReceiver = new DayViewClickReceiver(this);
        registerDayViewClickReceiver();

        buildMainView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterDayViewClickReceiver();
    }

    @Override
    public void OnCalendarDayViewClick(CalendarDate day) {
        Toast.makeText(this, day.toString(), Toast.LENGTH_LONG).show();
    }

    private void buildMainView() {
        CalendarDate today = new CalendarDate(Calendar.getInstance());
        mTextMainDay.setText(today.dayToString());
        mTextMainMonth.setText(Utils.dayOfWeekToString(today.getDayOfWeek()));
    }

    /**
     * Day view click receiver
     */

    private void registerDayViewClickReceiver() {
        if (mDayViewClickReceiver == null)
            return;

        IntentFilter filter = new IntentFilter(CalendarDayView.ACTION_CALENDAR_DAY_VIEW_CLICK);
        LocalBroadcastManager.getInstance(this).registerReceiver(mDayViewClickReceiver, filter);
    }

    private void unregisterDayViewClickReceiver() {
        if (mDayViewClickReceiver == null)
            return;

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mDayViewClickReceiver);
    }
}
