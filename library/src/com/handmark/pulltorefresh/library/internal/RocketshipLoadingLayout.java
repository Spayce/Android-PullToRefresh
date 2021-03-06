package com.handmark.pulltorefresh.library.internal;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.spayce_animation.RocketshipAnimation;

public class RocketshipLoadingLayout extends BaseLoadingLayout {

    private static final String TAG = "RocketshipLoadingLayout";

    private final float IMAGES_ASPECT_RATIO = 0.359375f;

    private Handler handler = new Handler();

    protected final PullToRefreshBase.Mode mMode;
    protected final PullToRefreshBase.Orientation mScrollDirection;

    private View mRocket;
    private RocketshipAnimation animation;

    public RocketshipLoadingLayout(final Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context);

        mMode = mode;
        mScrollDirection = scrollDirection;

        setBackgroundResource(R.color.rocketship_bg);
        LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_rocketship, this);

        mRocket = findViewById(R.id.iv_rocket);

        LayoutParams lp = new LayoutParams(getScreenWidth(), (int) (getScreenWidth() * IMAGES_ASPECT_RATIO));
        lp.gravity = Gravity.BOTTOM;
        mRocket.setLayoutParams(lp);

        animation = new RocketshipAnimation(mRocket);
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
        handler.post(new Runnable() {
            @Override
            public void run() {
                animation.startAnimation();
            }
        });
    }

    @Override
    public void pullToRefresh() {

    }

    @Override
    public void releaseToRefresh() {

    }

    @Override
    public void reset() {
        animation.reset();
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
        animation.onPull(scale);
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
