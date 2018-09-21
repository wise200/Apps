package memorytrainer.jwise200.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalMenu extends AppCompatActivity {
    private Button playAgain, mainMenu;
    private ImageView victoryDeclaration;
    private TextView victoryDeclarationText;
    private GameMode gameMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_menu);
        //Recieves winner and game mode from Game class
        gameMode = (GameMode) getIntent().getExtras().get("Game Mode");
        String winner = gameMode.winner;
        final boolean gameType = gameMode.isStandard;

        //assigns PIVs to their xml counterparts
        playAgain = (Button) findViewById(R.id.playAgainButton);
        mainMenu = (Button) findViewById(R.id.mainMenuButton);
        victoryDeclaration = (ImageView) findViewById(R.id.victoryDeclaration);
        victoryDeclarationText = (TextView) findViewById(R.id.victoryDeclarationText);

        //Customize Screen Appearance based on winner
        if (winner.equals("x"))
            victoryDeclaration.setImageResource(R.drawable.x);
        else if (winner.equals("o"))
            victoryDeclaration.setImageResource(R.drawable.o);
        else if (winner.equals("tie"))
        {
            victoryDeclaration.setImageResource(R.drawable.tie);
            victoryDeclarationText.setText(R.string.blank);
        }

        //Add OnClickListeners to buttons
        playAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalMenu.this, Game.class);
                intent.putExtra("Game Mode", gameMode); //standard game is true
                startActivity(intent);
            }
        });
        mainMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalMenu.this, MainActivity.class));
            }
        });

    }
}
