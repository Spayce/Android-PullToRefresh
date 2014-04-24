package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class TransparentLoadingLayout extends BaseLoadingLayout {

    private final float IMAGES_ASPECT_RATIO = 0.15f;

    protected final PullToRefreshBase.Mode mMode;
    protected final PullToRefreshBase.Orientation mScrollDirection;

    private View mRocket;

    public TransparentLoadingLayout(final Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context);


        mMode = mode;
        mScrollDirection = scrollDirection;

        setBackgroundColor(Color.TRANSPARENT);

        mRocket = new View(context);
        mRocket.setBackgroundColor(Color.TRANSPARENT);

        LayoutParams lp = new LayoutParams(getScreenWidth(), (int) (getScreenWidth() * IMAGES_ASPECT_RATIO));
        lp.gravity = Gravity.BOTTOM;
        mRocket.setLayoutParams(lp);
    }

    @Override
    public int getContentSize() {
        switch (mScrollDirection) {
            case HORIZONTAL:
                return mRocket.getWidth();
            case VERTICAL:
            default:
                return mRocket.getHeight();
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
        if(mRocket.getVisibility() == VISIBLE) {
            mRocket.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void showInvisibleViews() {
        if(mRocket.getVisibility() == INVISIBLE) {
            mRocket.setVisibility(VISIBLE);
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
