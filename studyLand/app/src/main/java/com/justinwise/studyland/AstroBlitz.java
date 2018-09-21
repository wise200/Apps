package com.justinwise.studyland;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.justinwise.studyland.Custom.AstroBlitzView;
import com.justinwise.studyland.R;

public class AstroBlitz extends AppCompatActivity {

    private AstroBlitzView game;
    private static final int FRAME_RATE = 33; //60 fps
    boolean[] selected;
    private Handler frame;
    private Runnable frameUpdate;
    private int count;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_blitz);
        game = (AstroBlitzView) findViewById(R.id.astroBlitzView);
        selected = getIntent().getExtras().getBooleanArray("sets");
        frame = new Handler();
        context = this;

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AstroBlitzView) view).shoot();
            }
        });

        count = 0;
        //Single frame
        frameUpdate = new Runnable() {
            @Override
            public void run() {
                frame.removeCallbacks(frameUpdate);

                if (count % 180 == 0)
                    game.addAsteroid();
                if (count % 1200 == 0)
                    game.addPlanet();
                count++;
                game.invalidate();
                frame.postDelayed(frameUpdate, FRAME_RATE);
            }
        };

        frame.postDelayed(frameUpdate, 250);
    }
}
