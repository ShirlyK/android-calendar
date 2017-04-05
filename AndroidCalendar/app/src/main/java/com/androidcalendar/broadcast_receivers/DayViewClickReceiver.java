package com.androidcalendar.broadcast_receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.androidcalendar.objects.CalendarDate;
import com.androidcalendar.views.CalendarDayView;
import com.androidcalendar.views.CalendarDayView.OnCalendarDayViewClickListener;

/**
 * Created by ShirlyKadosh on 4/4/17.
 */

public class DayViewClickReceiver extends BroadcastReceiver {

    private OnCalendarDayViewClickListener mListener;

    public DayViewClickReceiver(OnCalendarDayViewClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == CalendarDayView.ACTION_CALENDAR_DAY_VIEW_CLICK) {

            Bundle bundle = intent.getExtras();
            if(bundle == null)
                return;

            long millis = bundle.getLong(CalendarDayView.EXTRA_CALENDAR_DAY_VIEW_DATA);
            CalendarDate day = new CalendarDate(millis);
            if (mListener != null) {
                mListener.OnCalendarDayViewClick(day);
            }
        }
    }
}
