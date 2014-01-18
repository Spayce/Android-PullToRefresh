package com.handmark.pulltorefresh.library.spayce_animation;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.handmark.pulltorefresh.library.spayce_animation.animation.Animator;

import static com.handmark.pulltorefresh.library.spayce_animation.view.ViewPropertyAnimator.animate;


/**
 * Rocket animation. Not finished yet.
 * For now we use frame-by-frame animation
 * @author Evgeny
 */
public class RocketshipAnimation {
    private static String TAG = "RocketshipAnimation";

    final Handler handler;
    private final View view;
    private final double radius;
    private final double radius2;

    private Thread animationThread;

    public RocketshipAnimation(final View v, final double radius) {
        this.view = v;
        this.radius = radius;
        this.radius2 = radius * radius;
        this.handler = new Handler();

    }

    private int[] currentXY = new int[2];

    private XY currentXY() {
        view.getLocationOnScreen(currentXY);
        return new XY(currentXY[0], currentXY[1]);
    }

    public void startCircleAnimation() {
        animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                startLineUpAnimation();
            }

            private void startLineUpAnimation() {
                double progress = 0;
                double total = 130;

                final double lineStartUp = currentXY().y;
                final double lineStartEnd = currentXY().y - radius;

                while (!animationThread.isInterrupted() && progress < total) {
                    try {
                        Thread.sleep(5);
                        progress++;

                        final XY xy = lineUpStep(progress, total, lineStartUp, lineStartEnd);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                XY currentXY = currentXY();
                                animate(view).yBy((float) (xy.y - currentXY.y)).setDuration(0).start();
                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    } catch (Exception e) {
                        Log.d(TAG, "", e);
                    }
                }

                startCircleAnimation();
            }

            private void startCircleAnimation() {
                double progress = 0;
                double total = 360;

                int[] xyCenter = new int[2];
                view.getLocationInWindow(xyCenter);
                xyCenter[0] += radius;

                while (!animationThread.isInterrupted()) {
                    try {
                        Thread.sleep(5);
                        if(progress >= total) {
                            progress = 0;
                        } else {
                            progress++;
                        }

                        final double ratio = (double) progress / (double) total;
                        final XY xy = circleAnimStep(progress, total, xyCenter[0], xyCenter[1]);
                        makeStep(xy, ratio * 360);
                    } catch (InterruptedException e) {
                        break;
                    } catch (Exception e) {
                        Log.d(TAG, "", e);
                    }
                }
                animationThread = null;
            }
        });
        animationThread.start();
    }

    private void makeStep(final XY xy, final double rotation) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                XY currentXY = currentXY();
                animate(view).xBy((float) (xy.x - currentXY.x)).yBy((float) (xy.y - currentXY.y)).rotation((float) (rotation)).setDuration(0).start();
            }
        });
    }

    public void stopCirlceAnimation() {
        if(animationThread != null) {
            animationThread.interrupt();
        }
    }

    private XY lineUpStep(double progress, double total, double lineStartY, double lineEndY) {
        XY xy = currentXY();

        double ratio = progress / total;
        double deltaY = (lineEndY - lineStartY) * ratio;
        xy.y = lineStartY + deltaY;

        return xy;
    }

    private double getXRatio(double progress, double total) {
        final double ratio = (double) progress / (double) total;
        final double segmentLength = (double) ((Math.PI * 2) * ratio);
        final double xRatio = (double) Math.cos(segmentLength);

        double signFix = 1;
        if(ratio == 0) {
            signFix = -1;
        } if(ratio > 0 && ratio < 0.25f) {
            signFix = -1;
        } else if(ratio == 0.25f) {
            signFix = 0;
        } else if(ratio > 0.25f && ratio < 0.5f) {
            signFix = -1;
        } else if(ratio == 0.5f) {
            signFix = -1;
        } else if(ratio > 0.5f && ratio < 0.75f) {
            signFix = -1;
        } else if(ratio == 0.75f) {
            signFix = 0;
        } else if(ratio > 0.75f && ratio < 1) {
            signFix = -1;
        } else if(ratio == 1) {
            signFix = -1;
        }

        return xRatio * signFix;
    }

    private XY circleAnimStep(double progress, double total, double xCenter, double yCenter) {
        final double halfTotal = total / 2;

        double x = (double) (xCenter + (radius * getXRatio(progress, total)));

        final double xMinusxCenter2 = (x - xCenter) * (x - xCenter);
        double y = 0;
        if (progress < halfTotal) {
            y = yCenter - Math.sqrt(radius2 - xMinusxCenter2);
        } else if (progress > halfTotal) {
            y = yCenter + Math.sqrt(radius2 - xMinusxCenter2);
        } else {
            y = yCenter;
        }

        return new XY(x, y);
    }

    private static class XY {
        double x;
        double y;

        XY(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
}
