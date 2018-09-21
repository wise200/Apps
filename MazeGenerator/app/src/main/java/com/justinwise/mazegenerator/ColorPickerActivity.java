package com.justinwise.mazegenerator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import Custom.VerticalSeekBar;

public class ColorPickerActivity extends AppCompatActivity {
    TextView redText, greenText, blueText;
    VerticalSeekBar redBar, greenBar, blueBar;
    Button button;
    Vibrator vibrator;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        redText = (TextView) findViewById(R.id.redText);
        greenText = (TextView) findViewById(R.id.greenText);
        blueText = (TextView) findViewById(R.id.blueText);
        redBar = (VerticalSeekBar) findViewById(R.id.redBar);
        greenBar = (VerticalSeekBar) findViewById(R.id.greenBar);
        blueBar = (VerticalSeekBar) findViewById(R.id.blueBar);
        button = (Button) findViewById(R.id.button);

        extras = getIntent().getExtras();
        redBar.setProgress(extras.getInt("red"));
        greenBar.setProgress(extras.getInt("green"));
        blueBar.setProgress(extras.getInt("blue"));

        redText.setText(redBar.getProgress() + "");
        greenText.setText(greenBar.getProgress() + "");
        blueText.setText(blueBar.getProgress() + "");

        setTextColor();

        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                redText.setText(i + "");
                setTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }
        });

        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                greenText.setText(i + "");
                setTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }
        });

        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                blueText.setText(i + "");
                setTextColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vibrator.vibrate(10);
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("red", redBar.getProgress());
                intent.putExtra("green", greenBar.getProgress());
                intent.putExtra("blue", blueBar.getProgress());
                intent.putExtra("rows", extras.getInt("rows"));
                intent.putExtra("cols", extras.getInt("cols"));
                startActivity(intent);
            }
        });
    }

    private void setTextColor()
    {
        int red = redBar.getProgress();
        int green = greenBar.getProgress();
        int blue = blueBar.getProgress();
        redText.setTextColor(Color.rgb(red, green, blue));
        greenText.setTextColor(Color.rgb(red, green, blue));
        blueText.setTextColor(Color.rgb(red, green, blue));
    }
}
