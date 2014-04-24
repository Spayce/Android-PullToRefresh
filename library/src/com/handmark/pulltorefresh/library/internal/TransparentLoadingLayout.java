package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class TransparentLoadingLayout extends BaseLoadingLayout {

    protected final PullToRefreshBase.Mode mMode;
    protected final PullToRefreshBase.Orientation mScrollDirection;

    private View bgView;

    public TransparentLoadingLayout(final Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context);


        mMode = mode;
        mScrollDirection = scrollDirection;

        setBackgroundColor(Color.TRANSPARENT);

        bgView = new View(context);
        bgView.setBackgroundColor(Color.TRANSPARENT);

        final int height = dpToPx(100);
        LayoutParams lp = new LayoutParams(getScreenWidth(), height);
        lp.gravity = Gravity.BOTTOM;
        bgView.setLayoutParams(lp);

        addView(bgView);
    }

    @Override
    public int getContentSize() {
        switch (mScrollDirection) {
            case HORIZONTAL:
                return bgView.getWidth();
            case VERTICAL:
            default:
                return bgView.getHeight();
        }
    }

    @Override
    public void refreshing() {

    }

    @Override
    public void pullToRefresh() {

    }

    @Override
    public void releaseToRefresh() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setWidth(final int width) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.width = width;
        requestLayout();
    }

    @Override
    public void setHeight(final int height) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.height = height;
        requestLayout();
    }

    @Override
    public void onPull(float scale) {

    }

    @Override
    public void hideAllViews() {
        if(bgView.getVisibility() == VISIBLE) {
            bgView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void showInvisibleViews() {
        if(bgView.getVisibility() == INVISIBLE) {
            bgView.setVisibility(VISIBLE);
        }
    }

    private int dpToPx(final float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }

    private DisplayMetrics getDisplayMetrics() {
        final WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    private int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    //

    @Override
    public void setLastUpdatedLabel(CharSequence label) {

    }

    @Override
    public void setLoadingDrawable(Drawable drawable) {

    }

    @Override
    public void setPullLabel(CharSequence pullLabel) {

    }

    @Override
    public void setRefreshingLabel(CharSequence refreshingLabel) {

    }

    @Override
    public void setReleaseLabel(CharSequence releaseLabel) {

    }

    @Override
    public void setTextTypeface(Typeface tf) {

    }
}
