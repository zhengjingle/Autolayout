package com.zjl.autolayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * @author zhengjingle
 * @version 1.1
 * @date 2017/12/29
 *
 */
public class AutoUtils {

    public static int displayWidth;
    public static int displayHeight;

    private static int designWidth;
    private static int designHeight;

    private static double textPixelsRate;

    public static void setSize(Activity act, boolean hasStatusBar, int designWidth, int designHeight) {

        if (act == null || designWidth < 1 || designHeight < 1)
            return;

        Display display = act.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();


        if (hasStatusBar) {
            height -= getStatusBarHeight(act);
        }

        AutoUtils.displayWidth = width;
        AutoUtils.displayHeight = height;

        AutoUtils.designWidth = designWidth;
        AutoUtils.designHeight = designHeight;

        double displayDiagonal = Math.sqrt(Math.pow(AutoUtils.displayWidth, 2) + Math.pow(AutoUtils.displayHeight, 2));
        double designDiagonal = Math.sqrt(Math.pow(AutoUtils.designWidth, 2) + Math.pow(AutoUtils.designHeight, 2));

        AutoUtils.textPixelsRate = displayDiagonal / designDiagonal;

    }


    public static int getStatusBarHeight(Context context) {

        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier(
                    "status_bar_height", "dimen", "android");

            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }

        return result;

    }


    public static void auto(Activity act) {

        if (act == null || displayWidth < 1 || displayHeight < 1)
            return;

        View view = act.getWindow().getDecorView();
        auto(view);
    }


    public static void auto(View view) {

        if (view == null || displayWidth < 1 || displayHeight < 1)
            return;

        AutoUtils.autoTextSize(view);
        AutoUtils.autoSize(view);
        AutoUtils.autoPadding(view);
        AutoUtils.autoMargin(view);


        if (view instanceof ViewGroup) {
            auto((ViewGroup) view);
        }


    }


    private static void auto(ViewGroup viewGroup) {

        int count = viewGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            View child = viewGroup.getChildAt(i);

            if (child != null) {
                auto(child);
            }
        }

    }


    public static void autoMargin(View view) {

        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
            return;


        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (lp == null)
            return;


        lp.leftMargin = getDisplayWidthValue(lp.leftMargin);
        lp.topMargin = getDisplayHeightValue(lp.topMargin);
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin);
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin);

    }


    public static void autoPadding(View view) {

        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();


        l = getDisplayWidthValue(l);
        t = getDisplayHeightValue(t);
        r = getDisplayWidthValue(r);
        b = getDisplayHeightValue(b);

        view.setPadding(l, t, r, b);
    }


    public static void autoSize(View view) {

        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (lp == null)
            return;

        boolean isSquare = false;
        if (lp.width == lp.height) {
            isSquare = true;
        }

        if (lp.width > 0) {
            lp.width = getDisplayWidthValue(lp.width);
        }

        if (lp.height > 0) {
            lp.height = getDisplayHeightValue(lp.height);
        }

        if (isSquare) {
            if (lp.width > lp.height) {
                lp.width = lp.height;
            } else {
                lp.height = lp.width;
            }
        }

    }


    public static void autoTextSize(View view) {

        if (view instanceof TextView) {

            double designPixels = ((TextView) view).getTextSize();
            double displayPixels = textPixelsRate * designPixels;

            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) displayPixels);

        }

    }


    public static int getDisplayWidthValue(int designWidthValue) {

        if (Math.abs(designWidthValue) < 2) {
            return designWidthValue;
        }

        return designWidthValue * displayWidth / designWidth;

    }


    public static int getDisplayHeightValue(int designHeightValue) {

        if (Math.abs(designHeightValue) < 2) {
            return designHeightValue;
        }

        return designHeightValue * displayHeight / designHeight;

    }
    
    public static float getDisplayTextSize(float designTextSize){
        return (float) (AutoUtils.textPixelsRate*designTextSize);
    }

}
