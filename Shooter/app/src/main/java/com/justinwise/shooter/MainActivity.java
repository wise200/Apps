package com.justinwise.shooter;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.justinwise.shooter.Custom.Background;

public class MainActivity extends AppCompatActivity {

    private Handler frame;
    private static final int FRAME_RATE = 16;
    private TextView bottom, top;
    private Background canvas;
    private Runnable frameUpdate;
    private RelativeLayout screen;
    private Point asteroidVelocity;
    private Point saucerVelocity;
    private Point asteroidMaxPoint;
    private Point saucerMaxPoint;
    private boolean isAccelerating;
    private int count, score;
    private MenuItem scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom = (TextView) findViewById(R.id.bottom);
        top = (TextView) findViewById(R.id.top);
        canvas = (Background) findViewById(R.id.board);
        screen = (RelativeLayout) findViewById(R.id.activity_main);

        count = 0;
        score = 0;

        frame = new Handler();
        frameUpdate = new Runnable() {
            @Override
            public void run() {
                count++;
                frame.removeCallbacks(frameUpdate);
                if (canvas.wasCollision()) {
                    top.setText(R.string.collision);
                    String msg = "Score: " + score;
                    bottom.setText(msg);
                    score = 0;
                    msg = "Score: " + score;
                    scoreDisplay.setTitle(msg);
                    return;
                }
                Point asteroid = new Point(canvas.getAsteroidX(), canvas.getAsteroidY());
                Point saucer = new Point(canvas.getSaucerX(), canvas.getSaucerY());
                asteroid.x += asteroidVelocity.x;
                asteroid.y += asteroidVelocity.y;
                saucer.x += saucerVelocity.x;
                saucer.y += saucerVelocity.y;
                if (asteroid.x > asteroidMaxPoint.x || asteroid.x < 0) {
                    asteroidVelocity.x *= -1;
                    incrementScore();
                }
                if (asteroid.y > asteroidMaxPoint.y || asteroid.y < 0) {
                    asteroidVelocity.y *= -1;
                    incrementScore();
                }
                if (saucer.x > saucerMaxPoint.x || saucer.x < 0) {
                    saucerVelocity.x *= -1;
                    incrementScore();
                }
                if (saucer.y > saucerMaxPoint.y || saucer.y < 0) {
                    saucerVelocity.y *= -1;
                    incrementScore();
                }
                if (count % 5 == 0)
                   updateVelocity();
                canvas.setAsteroidPoint(asteroid);
                canvas.setSaucerPoint(saucer);
                canvas.invalidate();
                frame.postDelayed(this, FRAME_RATE);
            }
        };
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                placeSprites();
                frame.postDelayed(frameUpdate, FRAME_RATE);
            }
        }, 1000);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    private void updateVelocity() {
        int xDir = saucerVelocity.x > 0 ? 1 : -1;
        int yDir = saucerVelocity.y > 0 ? 1 : -1;

        int speed = isAccelerating ? Math.abs(saucerVelocity.x)+1 : Math.abs(saucerVelocity.x)-1;

        if (speed > 10)
            speed = 10;
        if (speed < 1)
            speed = 1;
        saucerVelocity.x = speed * xDir;
        saucerVelocity.y = speed * yDir;
    }

    private Point getRandomVelocity() {
        int x = (int) (Math.random() * 5) + 1;
        int y = (int) (Math.random() * 6) + 1;
        return new Point(x,y);
    }

    private void placeSprites() {
        do {
            int x1 = (int) (Math.random() * (canvas.getWidth() - canvas.getAsteroidWidth()));
            int y1 = (int) (Math.random() * (canvas.getHeight() - canvas.getAsteroidHeight()));
            canvas.setAsteroidPoint(new Point(x1, y1));

            int x2 = (int) (Math.random() * (canvas.getWidth() - canvas.getSaucerWidth()));
            int y2 = (int) (Math.random() * (canvas.getHeight() - canvas.getSaucerHeight()));
            canvas.setSaucerPoint(new Point(x2, y2));
            asteroidVelocity = getRandomVelocity();
            saucerVelocity = new Point(1, 1);
            asteroidMaxPoint = new Point(canvas.getWidth() - canvas.getAsteroidWidth(),
                    canvas.getHeight() - canvas.getSaucerHeight());
            saucerMaxPoint = new Point(canvas.getWidth() - canvas.getSaucerWidth(),
                    canvas.getHeight() - canvas.getSaucerHeight());
        } while (canvas.collisionCheck());
    }

    @Override
    synchronized public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN)
            isAccelerating = true;
        else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP)
            isAccelerating = false;
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.score, menu);
        scoreDisplay = menu.getItem(0);
        return true;
    }
    
    private void incrementScore() {
        score++;
        String msg = "Score: " + score;
        scoreDisplay.setTitle(msg);
    }

    private void reset() {
        frame.removeCallbacks(frameUpdate);
        placeSprites();
        canvas.resetCollisions();
        top.setText(R.string.warning);
        bottom.setText(R.string.count);
        frame.postDelayed(frameUpdate, FRAME_RATE);
    }
}
