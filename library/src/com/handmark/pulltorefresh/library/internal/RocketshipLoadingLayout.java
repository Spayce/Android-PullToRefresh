package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.spayce_animation.RocketshipAnimation;

public class RocketshipLoadingLayout extends BaseLoadingLayout {

    private static final String TAG = "RocketshipLoadingLayout";

    protected final PullToRefreshBase.Mode mMode;
    protected final PullToRefreshBase.Orientation mScrollDirection;

    private FrameLayout mInnerLayout;
    private View mRocket;
    private RocketshipAnimation animation;
    private Handler handler = new Handler();

    public RocketshipLoadingLayout(final Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context);
        mMode = mode;
        mScrollDirection = scrollDirection;

        setBackgroundResource(R.color.rocketship_bg);
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_rocketship, this);

        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
    }

    private void createRocket() {
        mRocket = new View(getContext());
        mRocket.setBackgroundResource(R.drawable.spayce_rocketship);
        LayoutParams lp = new LayoutParams(dpToPx(35), dpToPx(35));
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        mRocket.setLayoutParams(lp);
        addView(mRocket);
        animation = new RocketshipAnimation(mRocket, dpToPx(40));
    }

    private void destoyRocket() {
        if(mRocket != null) {
            removeView(mRocket);
            mRocket = null;
        }
        if(animation != null) {
            animation.stopCirlceAnimation();
            animation = null;
        }
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
        createRocket();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation.startCircleAnimation();
            }
        }, 300);
    }

    @Override
    public void pullToRefresh() {

    }

    @Override
    public void releaseToRefresh() {

    }

    @Override
    public void reset() {
        destoyRocket();
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

    private int dpToPx(final float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
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
