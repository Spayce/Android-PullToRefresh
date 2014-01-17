package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.handmark.pulltorefresh.library.ILoadingLayout;

public abstract class BaseLoadingLayout extends FrameLayout implements ILoadingLayout {
    public BaseLoadingLayout(final Context context) {
        super(context);
    }

    public BaseLoadingLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLoadingLayout(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    public abstract int getContentSize();

    public abstract void refreshing();

    public abstract void pullToRefresh();

    public abstract void releaseToRefresh();

    public abstract void reset();

    public abstract void setWidth(int width);

    public abstract void setHeight(int height);

    public abstract void onPull(float scale);

    public abstract void hideAllViews();

    public abstract void showInvisibleViews();

}
