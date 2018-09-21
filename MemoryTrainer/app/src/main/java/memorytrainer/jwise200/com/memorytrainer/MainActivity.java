package memorytrainer.jwise200.com.memorytrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button flashButton, checkButton;
    private TextView[] chars = new TextView[6];
    private int rand;
    private RadioGroup radioGroup;
    private RadioButton noSbutton, oneSbutton, twoSbutton, threeSbutton, fourSbutton, fiveSbutton, sixSbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flashButton = (Button) findViewById(R.id.generateSTVMButton);

        flashButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(MainActivity.this, STVMActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "I was clicked!", Toast.LENGTH_LONG).show();
                */
                switchToSTVM();
            }
        });


    }
    public void switchToSTVM()
    {
        setContentView(R.layout.activity_stvm);
        rand = (int) (Math.random() * 6);
        chars[0] = (TextView) findViewById(R.id.textView);
        chars[1] = (TextView) findViewById(R.id.textView2);
        chars[2] = (TextView) findViewById(R.id.textView3);
        chars[3] = (TextView) findViewById(R.id.textView4);
        chars[4] = (TextView) findViewById(R.id.textView5);
        chars[5] = (TextView) findViewById(R.id.textView6);

        for (int currentChar = 0; currentChar <= rand; currentChar++)
        {
            chars[currentChar].setText("S");
        }

        for (int currentChar = 5; currentChar > rand; currentChar--)
        {
            chars[currentChar].setText("T");
        }

        for (TextView currentChar : chars)
        {
            moveTextView(currentChar, (int) (Math.random() * 301) + 50, (int) (Math.random() * 501) + 50);
        }
        CountDownTimer timer = new CountDownTimer(1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                //do nothing
            }

            @Override
            public void onFinish() {
                switchToSTVMCheck();
            }
        };
    }

    private void switchToSTVMCheck()
    {
        setContentView(R.layout.stvm_check);

        checkButton = (Button) findViewById(R.id.checkButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        noSbutton = (RadioButton) findViewById(R.id.noSRadioButton);
        oneSbutton = (RadioButton) findViewById(R.id.oneSRadioButton);
        twoSbutton = (RadioButton) findViewById(R.id.twoSRadioButton);
        threeSbutton = (RadioButton) findViewById(R.id.threeSRadioButton);
        fourSbutton = (RadioButton) findViewById(R.id.fourSRadioButton);
        fiveSbutton = (RadioButton) findViewById(R.id.fiveSRadioButton);
        sixSbutton = (RadioButton) findViewById(R.id.sixSRadioButton);

        checkButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkSTVM();
            }
        });


    }
    private void checkSTVM()
    {
        //check the STVM
    }
    private void moveTextView(TextView textView, int left, int right)
    {
        float density = getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.setMargins((int) (left * density), (int) (right * density), (int) (200 * density), (int) (200 * density));
        textView.setLayoutParams(params);
    }

}
