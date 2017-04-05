package com.androidcalendar.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidcalendar.R;
import com.androidcalendar.adapters.CalendarViewPagerAdapter;
import com.androidcalendar.broadcast_receivers.DayViewClickReceiver;
import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.objects.CalendarMonth;
import com.androidcalendar.utils.Utils;
import com.androidcalendar.views.CalendarDayView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CalendarDayView.OnCalendarDayViewClickListener {

    private TextView mTextMainDay;
    private TextView mTextMainMonth;
    private TextView mPagerTextMonth;
    private ImageButton mButtonLeftArrow;
    private ImageButton mButtonRightArrow;
    private ViewPager mViewPager;
    private CalendarViewPagerAdapter mViewPagerAdapter;
    private DayViewClickReceiver mDayViewClickReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // main view
        mTextMainDay = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        mTextMainMonth = (TextView) findViewById(R.id.activity_main_text_month);

        // calendar view
        mViewPager = (ViewPager) findViewById(R.id.activity_main_view_pager);
        mPagerTextMonth = (TextView) findViewById(R.id.activity_main_pager_text_month);
        mButtonLeftArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_left_arrow);
        mButtonRightArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_right_arrow);
        mButtonLeftArrow.setOnClickListener(this);
        mButtonRightArrow.setOnClickListener(this);

        // day click receiver
        mDayViewClickReceiver = new DayViewClickReceiver(this);
        registerDayViewClickReceiver();

        buildMainView();
        buildCalendarView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_pager_button_right_arrow:
                int next = mViewPager.getCurrentItem() + 1;
                mViewPager.setCurrentItem(next, true);
                break;
            case R.id.activity_main_pager_button_left_arrow:
                int prev = mViewPager.getCurrentItem() - 1;
                mViewPager.setCurrentItem(prev, true);
                break;
        }
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

    private void buildCalendarView() {
        List<CalendarMonth> list = new ArrayList<>();
        CalendarMonth today = new CalendarMonth(Calendar.getInstance());

        list.add(new CalendarMonth(today, -2));
        list.add(new CalendarMonth(today, -1));
        list.add(today);
        list.add(new CalendarMonth(today, 1));
        list.add(new CalendarMonth(today, 2));

        mViewPagerAdapter = new CalendarViewPagerAdapter(list);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(2);
        mPagerTextMonth.setText(mViewPagerAdapter.getItemPageHeader(2));
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mPagerTextMonth.setText(mViewPagerAdapter.getItemPageHeader(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            int position = mViewPager.getCurrentItem();

            // current item is the first item in the list
            if (state == ViewPager.SCROLL_STATE_IDLE && position == 1) {
                addPrev();
            }

            // current item is the last item in the list
            if (state == ViewPager.SCROLL_STATE_IDLE && position == mViewPagerAdapter.getCount() - 2) {
                addNext();
            }

        }
    };

    private void addNext() {
        CalendarMonth month = mViewPagerAdapter.getItem(mViewPagerAdapter.getCount() - 1);
        mViewPagerAdapter.addNext(new CalendarMonth(month, 1));
    }

    private void addPrev() {
        CalendarMonth month = mViewPagerAdapter.getItem(0);
        mViewPagerAdapter.addPrev(new CalendarMonth(month, -1));
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
