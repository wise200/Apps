package com.example.a3015453.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.main_bg);

        bg.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            @Override
            public void onSwipeLeft() {
                startActivity(new Intent(MainActivity.this, ThisWeek.class));
            }

            @Override
            public void onSwipeRight() {
                startActivity(new Intent(MainActivity.this, MyClasses.class));
            }
        });
    }
}