package com.handmark.pulltorefresh.library.spayce_animation;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.handmark.pulltorefresh.library.R;


public class RocketshipAnimation {
    private static String TAG = "RocketshipAnimation";

    private Drawable[] drawables = new Drawable[27];

    private final View view;
    private Handler handler;

    public RocketshipAnimation(final View v) {
        this.view = v;
        this.handler = new Handler();
        initDrawables();
    }

    public void onPull(float scale) {
        if(scale > 1) {
            reset();
        } else {
            final int pos = (int) ((drawables.length - 1) * scale);
            view.setBackgroundDrawable(drawables[pos]);
        }
    }

    public void reset() {
        try {
            ((AnimationDrawable) view.getBackground()).stop();
        } catch (Exception e) {
            //no-op
        }
        view.setBackgroundResource(R.color.rocketship_bg);
    }

    public void startAnimation() {
        playAnimationDrawable(R.drawable.rocket_up);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playAnimationDrawable(R.drawable.rocket_circle);
            }
        }, 420 /*Length of rocket_up animation*/);
    }


    private void playAnimationDrawable(final int resId) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(resId);
                ((AnimationDrawable) view.getBackground()).start();
            }
        });
    }

    private void initDrawables() {
        final Resources res = view.getContext().getResources();
        drawables[0] = res.getDrawable(R.drawable.pull_to_refresh_0);
        drawables[1] = res.getDrawable(R.drawable.pull_to_refresh_1);
        drawables[2] = res.getDrawable(R.drawable.pull_to_refresh_2);
        drawables[3] = res.getDrawable(R.drawable.pull_to_refresh_3);
        drawables[4] = res.getDrawable(R.drawable.pull_to_refresh_4);
        drawables[5] = res.getDrawable(R.drawable.pull_to_refresh_5);
        drawables[6] = res.getDrawable(R.drawable.pull_to_refresh_6);
        drawables[7] = res.getDrawable(R.drawable.pull_to_refresh_7);
        drawables[8] = res.getDrawable(R.drawable.pull_to_refresh_8);
        drawables[9] = res.getDrawable(R.drawable.pull_to_refresh_9);
        drawables[10] = res.getDrawable(R.drawable.pull_to_refresh_10);
        drawables[11] = res.getDrawable(R.drawable.pull_to_refresh_11);
        drawables[12] = res.getDrawable(R.drawable.pull_to_refresh_12);
        drawables[13] = res.getDrawable(R.drawable.pull_to_refresh_13);
        drawables[14] = res.getDrawable(R.drawable.pull_to_refresh_14);
        drawables[15] = res.getDrawable(R.drawable.pull_to_refresh_15);
        drawables[16] = res.getDrawable(R.drawable.pull_to_refresh_16);
        drawables[17] = res.getDrawable(R.drawable.pull_to_refresh_17);
        drawables[18] = res.getDrawable(R.drawable.pull_to_refresh_18);
        drawables[19] = res.getDrawable(R.drawable.pull_to_refresh_19);
        drawables[20] = res.getDrawable(R.drawable.pull_to_refresh_20);
        drawables[21] = res.getDrawable(R.drawable.pull_to_refresh_21);
        drawables[22] = res.getDrawable(R.drawable.pull_to_refresh_22);
        drawables[23] = res.getDrawable(R.drawable.pull_to_refresh_23);
        drawables[24] = res.getDrawable(R.drawable.pull_to_refresh_24);
        drawables[25] = res.getDrawable(R.drawable.pull_to_refresh_25);
        drawables[26] = res.getDrawable(R.drawable.pull_to_refresh_26);
    }

}
