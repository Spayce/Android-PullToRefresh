package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class RocketshipLoadingLayout extends BaseLoadingLayout {

    public RocketshipLoadingLayout(final Context context) {
        super(context);
    }

    public RocketshipLoadingLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public RocketshipLoadingLayout(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getContentSize() {
        return 0;
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

    }

    @Override
    public void setHeight(final int height) {

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
