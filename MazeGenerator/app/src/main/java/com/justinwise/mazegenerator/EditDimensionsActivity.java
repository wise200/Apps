package com.justinwise.mazegenerator;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import Custom.VerticalSeekBar;

public class EditDimensionsActivity extends AppCompatActivity {
    TextView rowText, colText;
    VerticalSeekBar rowBar, colBar;
    Button button;
    Vibrator vibrator;
    Bundle extras;
    RadioButton lockButton;
    boolean isLocked;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dimensions);

        rowBar = (VerticalSeekBar) findViewById(R.id.rowBar);
        colBar = (VerticalSeekBar) findViewById(R.id.colBar);
        rowText = (TextView) findViewById(R.id.rowText);
        colText = (TextView) findViewById(R.id.colText);
        button = (Button) findViewById(R.id.button);
        lockButton = (RadioButton) findViewById(R.id.lockButton);
        group = (RadioGroup) findViewById(R.id.radioGroup);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        extras = getIntent().getExtras();
        rowBar.setProgress(extras.getInt("rows") - 1);
        colBar.setProgress(extras.getInt("cols") - 1);

        rowText.setText(rowBar.getProgress() + 1 + "");
        colText.setText(colBar.getProgress() + 1 + "");

        isLocked = false;

        rowBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (isLocked)
                {
                    int cols = convertToCols(i);
                    colBar.setProgress(cols);
                    colText.setText(cols + 1 + "");
                }


                rowText.setText(i + 1 + "");
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

        colBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (isLocked)
                {
                    int rows = convertToRows(i);
                    rowBar.setProgress(rows);
                    rowText.setText(rows + 1 + "" );
                }

                colText.setText(i + 1 + "");
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
                intent.putExtra("rows", rowBar.getProgress() + 1);
                intent.putExtra("cols", colBar.getProgress() + 1);
                intent.putExtra("red", extras.getInt("red"));
                intent.putExtra("green", extras.getInt("green"));
                intent.putExtra("blue", extras.getInt("blue"));
                startActivity(intent);
            }
        });



        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                isLocked = !isLocked;
                if (!isLocked)
                    group.clearCheck();
            }
        });
    }

    private int convertToRows(int cols)
    {
        double frac = cols / 70.0;
        return (int) (frac * 100);
    }

    private int convertToCols(int rows)
    {
        double frac = rows / 100.0;
        return (int) (frac * 70);
    }
}
