package com.androidcalendar.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


public class Utils {

    public static int getScreenWidth(Context context) {
        return getPointSize(context).x;
    }

    public static int getScreenHeight(Context context) {
        return getPointSize(context).y;
    }

    private static Point getPointSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
