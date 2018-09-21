package com.justinwise.tapbattle;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.content.res.Resources;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Battle extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private boolean mVisible;
    private TextView redZone, blueZone;
    private int redScore, screenHeight, increment;
    private final int numIncrements = 20;
    private int red, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battle);

        mVisible = true;
        redZone = (TextView) findViewById(R.id.redSide);
        blueZone = (TextView) findViewById(R.id.blueSide);
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        increment = screenHeight / numIncrements;

        red = getResources().getColor(R.color.red);
        blue = getResources().getColor(R.color.blue);

        newGame();


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    private void newGame()
    {
        redScore = numIncrements / 2;

        blueZone.setHeight(screenHeight);
        blueZone.setText("3");
        changeColor(blueZone, blue);

        redZone.setHeight(screenHeight / 2);
        redZone.setText("3");
        changeColor(redZone, red);

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
                redZone.setText(l / 1000 + "");
                blueZone.setText(l / 1000 + "");
            }

            @Override
            public void onFinish() {
                redZone.setText(R.string.tap_here);
                redZone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redScore++;
                        redZone.setHeight(redZone.getHeight() + increment);
                        checkForWin();
                    }
                });

                blueZone.setText(R.string.tap_here);
                blueZone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        redScore--;
                        redZone.setHeight(redZone.getHeight() - increment);
                        checkForWin();
                    }
                });
            }
        }.start();

    }

    private void checkForWin()
    {
        if (redScore <= 0)
        {
            disableClicks();
            celebrate(blueZone);
        }
        else if (redScore >= numIncrements) {
            disableClicks();
            celebrate(redZone);
        }
    }

    private void celebrate(TextView newVictor)
    {
        final TextView victor = newVictor;
        victor.setText(R.string.congrats);
        new CountDownTimer(3000, 500) {
            @Override
            public void onTick(long l) {
                switchColor(victor);
            }

            @Override
            public void onFinish() {
                newGame();
            }
        }.start();
    }

    private void switchColor(TextView view)
    {
        if (view.getTag().equals(new Integer(red)))
            changeColor(view, blue);
        else changeColor(view, red);
    }

    private void disableClicks()
    {
        redZone.setOnClickListener(null);
        blueZone.setOnClickListener(null);
    }

    private void changeColor(TextView view, int color)
    {
        view.setBackgroundColor(color);
        view.setTag(new Integer(color));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            /*
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            */
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        //mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                //| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            //mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
