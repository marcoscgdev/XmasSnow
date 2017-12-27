package com.marcoscg.xmassnow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

@SuppressWarnings("all")
public class SnowView extends View {

    private static Random sRandomGen = new Random();
    private boolean isInterval = false;
    private String startDate, endDate;

    private int snow_flake_count = 80;
    private final List<Drawable> drawables = new ArrayList<Drawable>();
    private int[][] coords;
    private final Context mContext;

    public SnowView(Context context) {
        super(context);
        setFocusable(false);
        setFocusableInTouchMode(false);
        mContext = context;
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(false);
        setFocusableInTouchMode(false);
        mContext = context;
    }

    public SnowView setInterval(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        isInterval = true;
        return this;
    }

    public SnowView onlyOnChristmas(boolean onlyOnChristmas) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (onlyOnChristmas)
            setInterval("12/25/"+year, "12/26/"+year);
        return this;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        if (showSnow()) {
            Interpolator interpolator = new LinearInterpolator();
            snow_flake_count = Math.max(width, height) / 60;
            coords = new int[snow_flake_count][];
            drawables.clear();
            for (int i = 0; i < snow_flake_count; i++) {
                Animation animation = new TranslateAnimation(0, height / 10
                        - sRandomGen.nextInt(height / 5), 0, height + 30);
                animation.setDuration(10 * height + sRandomGen.nextInt(5 * height));
                animation.setRepeatCount(-1);
                animation.initialize(10, 10, 10, 10);
                animation.setDuration(6000);
                animation.setInterpolator(interpolator);

                coords[i] = new int[] { sRandomGen.nextInt(width - 30), -60 };
                int size = 18 * height / 1000 + sRandomGen.nextInt(13);
                Drawable snow_flake = mContext.getResources().getDrawable(R.drawable.snow);

                snow_flake.setBounds(0, 0, size, size);
                drawables.add(new AnimateDrawable(snow_flake, animation));
                animation.setStartOffset(sRandomGen.nextInt(20 * height));
                animation.startNow();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (showSnow()) {
            for (int i = 0; i < snow_flake_count; i++) {
                Drawable drawable = drawables.get(i);
                canvas.save();
                canvas.translate(coords[i][0], coords[i][1]);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
        invalidate();
    }

    private boolean showSnow() {
        if (isInterval) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date sDate = sdf.parse(startDate);
                Date eDate = sdf.parse(endDate);
                return new Date().after(sDate) && new Date().before(eDate);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            return true;
        } else return true;
    }

}