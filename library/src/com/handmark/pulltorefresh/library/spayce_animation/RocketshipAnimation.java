package com.handmark.pulltorefresh.library.spayce_animation;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.handmark.pulltorefresh.library.R;


public class RocketshipAnimation {
    private static String TAG = "RocketshipAnimation";


    private Activity context;

    private Drawable[] drawables = new Drawable[42];

    private final View view;
    private SpayceAnimationDrawable startStage;
    private SpayceAnimationDrawable cycleStage;

    public RocketshipAnimation(final Activity context, final View v) {
        this.context = context;
        this.view = v;
        initDrawables();

        startStage = new SpayceAnimationDrawable();
        for (int i = 0; i < drawables.length; ++i) {
            startStage.addFrame(drawables[i], 40);
        }
        startStage.setAnimationFinishListener(new SpayceAnimationDrawable.IAnimationFinishListener() {
            @Override
            public void onAnimationFinished() {
                context.runOnUiThread(new Runnable() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void run() {
                        try {
                            view.setBackgroundDrawable(cycleStage);
                            cycleStage.start();
                        } catch (Exception e) {
                            //no-op
                        }
                    }
                });
            }
        });


        cycleStage = new SpayceAnimationDrawable();
        for (int i = 14; i < drawables.length; ++i) {
            cycleStage.addFrame(drawables[i], 45);
        }
        cycleStage.setOneShot(false);
    }

    public void onPull(float scale) {
        if(scale > 1) {
        } else {
            final int pos = (int) ((drawables.length - 1) * scale);
            view.setBackgroundDrawable(drawables[pos]);
        }
    }

    public void reset() {
        try {
            startStage.reset();
            cycleStage.reset();
        } catch (Exception e) {
            //no-op
        }
    }

    public void startAnimation() {
        context.runOnUiThread(new Runnable() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                reset();
                view.setBackgroundDrawable(startStage);
                startStage.start();
            }
        });
    }

    private void initDrawables() {
        final Resources res = view.getContext().getResources();

        drawables[0] = res.getDrawable(R.drawable.pull_to_refresh_1);
        drawables[1] = res.getDrawable(R.drawable.pull_to_refresh_2);
        drawables[2] = res.getDrawable(R.drawable.pull_to_refresh_3);
        drawables[3] = res.getDrawable(R.drawable.pull_to_refresh_4);
        drawables[4] = res.getDrawable(R.drawable.pull_to_refresh_5);
        drawables[5] = res.getDrawable(R.drawable.pull_to_refresh_6);
        drawables[6] = res.getDrawable(R.drawable.pull_to_refresh_7);
        drawables[7] = res.getDrawable(R.drawable.pull_to_refresh_8);
        drawables[8] = res.getDrawable(R.drawable.pull_to_refresh_9);
        drawables[9] = res.getDrawable(R.drawable.pull_to_refresh_10);
        drawables[10] = res.getDrawable(R.drawable.pull_to_refresh_11);
        drawables[11] = res.getDrawable(R.drawable.pull_to_refresh_12);
        drawables[12] = res.getDrawable(R.drawable.pull_to_refresh_13);
        drawables[13] = res.getDrawable(R.drawable.pull_to_refresh_14);

        //<cycle>
        drawables[14] = res.getDrawable(R.drawable.pull_to_refresh_15);
        drawables[15] = res.getDrawable(R.drawable.pull_to_refresh_16);
        drawables[16] = res.getDrawable(R.drawable.pull_to_refresh_17);
        drawables[17] = res.getDrawable(R.drawable.pull_to_refresh_18);
        drawables[18] = res.getDrawable(R.drawable.pull_to_refresh_19);
        drawables[19] = res.getDrawable(R.drawable.pull_to_refresh_20);
        drawables[20] = res.getDrawable(R.drawable.pull_to_refresh_21);
        drawables[21] = res.getDrawable(R.drawable.pull_to_refresh_22);
        drawables[22] = res.getDrawable(R.drawable.pull_to_refresh_23);
        drawables[23] = res.getDrawable(R.drawable.pull_to_refresh_24);
        drawables[24] = res.getDrawable(R.drawable.pull_to_refresh_25);
        drawables[25] = res.getDrawable(R.drawable.pull_to_refresh_26);
        drawables[26] = res.getDrawable(R.drawable.pull_to_refresh_27);
        drawables[27] = res.getDrawable(R.drawable.pull_to_refresh_28);
        drawables[28] = res.getDrawable(R.drawable.pull_to_refresh_29);
        drawables[29] = res.getDrawable(R.drawable.pull_to_refresh_30);
        drawables[30] = res.getDrawable(R.drawable.pull_to_refresh_31);
        drawables[31] = res.getDrawable(R.drawable.pull_to_refresh_32);
        drawables[32] = res.getDrawable(R.drawable.pull_to_refresh_33);
        drawables[33] = res.getDrawable(R.drawable.pull_to_refresh_34);
        drawables[34] = res.getDrawable(R.drawable.pull_to_refresh_35);
        drawables[35] = res.getDrawable(R.drawable.pull_to_refresh_36);
        drawables[36] = res.getDrawable(R.drawable.pull_to_refresh_37);
        drawables[37] = res.getDrawable(R.drawable.pull_to_refresh_38);
        drawables[38] = res.getDrawable(R.drawable.pull_to_refresh_39);
        drawables[39] = res.getDrawable(R.drawable.pull_to_refresh_40);
        drawables[40] = res.getDrawable(R.drawable.pull_to_refresh_41);
        drawables[41] = res.getDrawable(R.drawable.pull_to_refresh_42);
        //</cycle>
    }

}
