package com.androidcalendar.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.androidcalendar.objects.CalendarMonth;
import com.androidcalendar.views.CalendarMonthView;

import java.util.List;


public class CalendarViewPagerAdapter extends PagerAdapter {

    List<CalendarMonth> mData;

    public CalendarViewPagerAdapter(List<CalendarMonth> list) {
        mData = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CalendarMonth month = mData.get(position);
        CalendarMonthView calendarView = new CalendarMonthView(container.getContext());
        calendarView.buildView(month);
        (container).addView(calendarView, 0);
        calendarView.setTag(month);
        return calendarView;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        View view = (View) object;
        CalendarMonth month = (CalendarMonth) view.getTag();
        int position = mData.indexOf(month);

        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
    }

    public void addNext(CalendarMonth month) {
        mData.add(month);
        notifyDataSetChanged();
    }

    public void addPrev(CalendarMonth month) {
        mData.add(0, month);
        notifyDataSetChanged();
    }

    public String getItemPageHeader(int position) {
        return mData.get(position).toString();
    }

    public CalendarMonth getItem(int position) {
        return mData.get(position);
    }
}
