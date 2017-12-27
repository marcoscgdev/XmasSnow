package com.marcoscg.xmassnow;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.Calendar;

/**
 * Created by marco on 27/12/2017.
 */

public class XmasSnow {

    private static WeakReference<Activity> act;
    private boolean belowActionBar = false, belowStatusBar = true, isInterval = false, onlyOnChristmas = false;
    private String startDate, endDate;

    public static XmasSnow on(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity can not be null");
        }
        act = new WeakReference<>(activity);
        return new XmasSnow();
    }

    public XmasSnow belowActionBar(boolean belowActionBar) {
        this.belowActionBar = belowActionBar;
        return this;
    }

    public XmasSnow belowStatusBar(boolean belowStatusBar) {
        this.belowStatusBar = belowStatusBar;
        return this;
    }

    public XmasSnow setInterval(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        isInterval = true;
        return this;
    }

    public XmasSnow onlyOnChristmas(boolean onlyOnChristmas) {
        this.onlyOnChristmas = onlyOnChristmas;
        return this;
    }

    public void start() {
        Window window = getActivity().getWindow();
        SnowView snowView = new SnowView(getActivity());
        if (isInterval)
            snowView.setInterval(startDate, endDate);
        if (onlyOnChristmas)
            snowView.onlyOnChristmas(true);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (belowActionBar) {
            if (Build.VERSION.SDK_INT >= 19)
                layoutParams.topMargin = getActionBarHeight(getActivity())+getStatusBarHeight();
            else layoutParams.topMargin = getActionBarHeight(getActivity());
        }
        if (belowStatusBar && !belowActionBar)
            layoutParams.topMargin = getStatusBarHeight();
        ((ViewGroup) window.getDecorView()).addView(snowView, layoutParams);
    }

    private int getActionBarHeight(Activity activity) {
        TypedValue typedValue = new TypedValue();
        int attributeResourceId = android.R.attr.actionBarSize;
        if (activity instanceof AppCompatActivity) {
            attributeResourceId = R.attr.actionBarSize;
        }
        if (activity.getTheme().resolveAttribute(attributeResourceId, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, activity.getResources().getDisplayMetrics());
        }
        return (int) Math.floor(activity.getResources()
                .getDimensionPixelSize(R.dimen.default_actionbar_height));
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getActivity().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private Activity getActivity() {
        return act.get();
    }
}
