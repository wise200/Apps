package memorytrainer.jwise200.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private ToggleButton playerNumButton, gameTypeButton;
    private boolean isSinglePlayer = true, isStandard = true;
    private GameMode gameMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.playButton);
        playerNumButton = (ToggleButton) findViewById(R.id.playerNumButton);
        gameTypeButton = (ToggleButton) findViewById(R.id.gameTypeButton);

        playerNumButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSinglePlayer = !isChecked;
            }
        });

        gameTypeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isStandard = !isChecked;
            }
        });

        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                gameMode = new GameMode(isStandard, isSinglePlayer);
                intent.putExtra("Game Mode", gameMode);
                startActivity(intent);
            }
        });
    }
}
