package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

public class RocketshipLoadingLayout extends BaseLoadingLayout {

    private static final String TAG = "RocketshipLoadingLayout";

    protected final PullToRefreshBase.Mode mMode;
    protected final PullToRefreshBase.Orientation mScrollDirection;

    private FrameLayout mInnerLayout;

    public RocketshipLoadingLayout(final Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context);
        mMode = mode;
        mScrollDirection = scrollDirection;

        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_rocketship, this);

        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
    }

    @Override
    public int getContentSize() {
        switch (mScrollDirection) {
            case HORIZONTAL:
                return mInnerLayout.getWidth();
            case VERTICAL:
            default:
                return mInnerLayout.getHeight();
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
    public void onPull(final float scale) {

    }

    @Override
    public void hideAllViews() {

    }

    @Override
    public void showInvisibleViews() {

    }


    /* ************************
     *         UNUSED
     * ************************/

    @Override
    public void setLastUpdatedLabel(final CharSequence label) {
    }

    @Override
    public void setLoadingDrawable(final Drawable drawable) {
    }

    @Override
    public void setPullLabel(final CharSequence pullLabel) {
    }

    @Override
    public void setRefreshingLabel(final CharSequence refreshingLabel) {
    }

    @Override
    public void setReleaseLabel(final CharSequence releaseLabel) {
    }

    @Override
    public void setTextTypeface(final Typeface tf) {
    }
}
