package memorytrainer.jwise200.com.memorytrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class STVMActivity extends AppCompatActivity {
    //TextView char1, char2, char3, char4, char5, char6;
    TextView[] chars = new TextView[6];
    //float density = getResources().getDisplayMetrics().density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stvm);
        int rand = (int) (Math.random() * 6);
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
    }

    private void moveTextView(TextView textView, int left, int right)
    {
        float density = getResources().getDisplayMetrics().density;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.setMargins((int) (left * density), (int) (right * density), (int) (200 * density), (int) (200 * density));
        textView.setLayoutParams(params);
    }
}
