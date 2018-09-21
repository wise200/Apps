package com.justinwise.ballbouncer;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import Custom.Ball;
import Custom.Constants;
import Custom.ViewList;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout;
    int red, green, blue;
    //View ball;
    //ObjectAnimator animation;
    RelativeLayout.LayoutParams params;
    ViewList views;
    ArrayList<Ball> balls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.activity_main);

        views = new ViewList();

        Intent oldIntent = getIntent();
        if (oldIntent.hasExtra(Constants.red))
        {
            Bundle extras = oldIntent.getExtras();
            red = extras.getInt(Constants.red);
            green = extras.getInt(Constants.green);
            blue = extras.getInt(Constants.blue);
            balls = extras.getParcelableArrayList(Constants.balls);
            for (Ball ball : balls)
                views.add(ball.addView(this, layout));
        } else {
            red = 255;
            green = 255;
            blue = 255;
            balls = new ArrayList<Ball>();
        }

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    throwBall(motionEvent.getX(), motionEvent.getY());
                return true;
            }
        });

        /*
        ball = (View) findViewById(R.id.ball);

        PropertyValuesHolder xProp = PropertyValuesHolder.ofFloat("x", 0f);
        PropertyValuesHolder yProp = PropertyValuesHolder.ofFloat("y", 0f);

        animation = ObjectAnimator.ofPropertyValuesHolder(ball, xProp, yProp);
        animation.setDuration(500);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.start();
            }
        });
        */
    }

    private void throwBall(float x, float y)
    {
        Ball ball = new Ball(x, y, 100, 100, red, green, blue);
        View view = ball.addView(this, layout);
        balls.add(ball);
        views.add(view);

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.Y, 0f);
        animation.setDuration(500);
        animation.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (item.getItemId() == R.id.changeColor) {
            Intent intent = new Intent(this, ColorPickerActivity.class);
            intent.putExtra(Constants.red, red);
            intent.putExtra(Constants.green, green);
            intent.putExtra(Constants.blue, blue);
            intent.putParcelableArrayListExtra(Constants.balls, balls);

            startActivity(intent);
        } else {
            views.deleteAll();
            for (int i = balls.size() - 1; i >= 0; i--)
                balls.remove(i);
        }


        return true;
        //return super.onOptionsItemSelected(item);
    }
}
