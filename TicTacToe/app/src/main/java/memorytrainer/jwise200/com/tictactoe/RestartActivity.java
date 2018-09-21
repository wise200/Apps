package memorytrainer.jwise200.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RestartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(RestartActivity.this, Game.class);
        intent.putExtra("Game Mode", (GameMode) getIntent().getExtras().get("Game Mode"));
        startActivity(intent);
    }
}
