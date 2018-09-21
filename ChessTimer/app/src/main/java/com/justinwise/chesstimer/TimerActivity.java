package com.justinwise.chesstimer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.justinwise.chesstimer.customclasses.Player;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    private View inner, middle, outer;
    private TextView time, name, rotatedName;
    private ArrayList<Player> players;
    private int pos;
    private CountDownTimer timer;
    private Vibrator vibrator;
    private boolean isPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Bundle extras = getIntent().getExtras();
        players =  extras.getParcelableArrayList("players");

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        time = (TextView) findViewById(R.id.currentTime);
        name = (TextView) findViewById(R.id.currentName);
        rotatedName = (TextView) findViewById(R.id.rotatedName);
        inner = (View) findViewById(R.id.inner);
        middle = (View) findViewById(R.id.middle);
        outer = (View) findViewById(R.id.outer);
        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        
        pos = 0;
        isPaused = false;
        
        updateScreen();

        int diameter1 = 410;
        int diameter2 = 460;
        int diameter3 = 510;


        ValueAnimator innerAnim1 = ValueAnimator.ofInt(dptopx(260), dptopx(diameter1));
        ValueAnimator innerAnim2 = ValueAnimator.ofInt(dptopx(260), dptopx(diameter2));
        ValueAnimator innerAnim3 = ValueAnimator.ofInt(dptopx(260), dptopx(diameter3));
        ValueAnimator innerAnim4 = ValueAnimator.ofInt(dptopx(260), dptopx(diameter2));

        ValueAnimator middleAnim1 = ValueAnimator.ofInt(dptopx(295), dptopx(diameter1));
        ValueAnimator middleAnim2 = ValueAnimator.ofInt(dptopx(295), dptopx(diameter2));
        ValueAnimator middleAnim3 = ValueAnimator.ofInt(dptopx(295), dptopx(diameter3));
        ValueAnimator middleAnim4 = ValueAnimator.ofInt(dptopx(295), dptopx(diameter2));

        ValueAnimator outerAnim1 = ValueAnimator.ofInt(dptopx(330), dptopx(diameter1));
        ValueAnimator outerAnim2 = ValueAnimator.ofInt(dptopx(330), dptopx(diameter2));
        ValueAnimator outerAnim3 = ValueAnimator.ofInt(dptopx(330), dptopx(diameter3));
        ValueAnimator outerAnim4 = ValueAnimator.ofInt(dptopx(330), dptopx(diameter2));


        innerAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(inner, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        innerAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(inner, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        innerAnim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(inner, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        innerAnim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(inner, (Integer) valueAnimator.getAnimatedValue());
            }
        });


        middleAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(middle, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        middleAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(middle, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        middleAnim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(middle, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        middleAnim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(middle, (Integer) valueAnimator.getAnimatedValue());
            }
        });


        outerAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(outer, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        outerAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(outer, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        outerAnim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(outer, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        outerAnim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                updateParams(outer, (Integer) valueAnimator.getAnimatedValue());
            }
        });



        AnimatorSet set = new AnimatorSet();
        set.playTogether(innerAnim1, middleAnim1, outerAnim1);
        set.play(innerAnim2).after(innerAnim1);
        set.play(innerAnim3).after(innerAnim2);
        set.play(innerAnim4).after(innerAnim3);
        set.play(middleAnim2).after(middleAnim1);
        set.play(middleAnim3).after(middleAnim2);
        set.play(middleAnim4).after(middleAnim3);
        set.play(outerAnim2).after(outerAnim1);
        set.play(outerAnim3).after(outerAnim2);
        set.play(outerAnim4).after(outerAnim3);
        ArrayList<Animator> anims = set.getChildAnimations();
        set.setDuration(3000);
        for (Animator anim : anims) {
            ((ValueAnimator) anim).setRepeatCount(1);
            ((ValueAnimator) anim).setRepeatMode(ValueAnimator.REVERSE);
        }
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animator.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();

        updateTimer();

        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePlayer();
            }
        });
    }

    private void updateParams(View view, int diameter) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = diameter;
        layoutParams.width = diameter;
        view.setLayoutParams(layoutParams);
    }

    private int dptopx(float dp) {
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        return (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    private void updateScreen() {
        time.setText(currentPlayer().getTime());
        name.setText(currentPlayer().getName());
        rotatedName.setText(currentPlayer().getName());
        GradientDrawable bg = (GradientDrawable) inner.getBackground();
        int width = dptopx(3);
        int color = Color.parseColor(currentPlayer().getColor().toString());
        bg.setStroke(width, color);
        bg = (GradientDrawable) middle.getBackground();
        bg.setStroke(width, color);
        bg = (GradientDrawable) outer.getBackground();
        bg.setStroke(width, color);

    }
    private Player currentPlayer() {
        return players.get(pos);
    }

    private void changePlayer() {
        pos = (pos + 1) % players.size();
        updateScreen();
        updateTimer();
    }

    private void updateTimer() {
        if (timer != null)
            timer.cancel();
        timer = new CountDownTimer(currentPlayer().getMillis(), 100) {
            @Override
            public void onTick(long l) {
                currentPlayer().setMillis(l);
                time.setText(currentPlayer().getTime());
            }

            @Override
            public void onFinish() {
                vibrator.vibrate(1000);
                changePlayer();
            }
        };
        timer.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pause) {
            if (isPaused) {
                updateTimer();
            } else {
                timer.cancel();
            }
            isPaused = !isPaused;
        } else {
            pos = 0;
            timer.cancel();
            for (Player player : players)
                player.reset();
            updateScreen();
            updateTimer();
        }
        return true;
    }
}
