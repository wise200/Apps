package memorytrainer.jwise200.com.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Game extends AppCompatActivity {

    private ImageView spot1, spot2, spot3, spot4, spot5, spot6, spot7, spot8, spot9;
    private boolean gameType, turn; //true == x's turn, false == o's turn
    private TextView gameTypeSignifier;
    private ImageView[] boardImages;
    private int moveCounter;
    private Button restartButton;
    private GameMode gameMode;
    private AI ai = new AI();
    boolean isSinglePlayer;
    //private ArrayList<ImageView, Integer> reverseBoardImages;
    private int[] board; //0 == Space hasn't been clicked, 1 == X owns space, 2 == O owns space
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Assign all PIVs to their xml counterparts

        gameMode = (GameMode) getIntent().getExtras().get("Game Mode"); //true means standard, false means reverse
        gameType = gameMode.isStandard;
        isSinglePlayer = gameMode.isSinglePlayer;
        gameTypeSignifier = (TextView) findViewById(R.id.gameTypeSignifier);
        restartButton = (Button) findViewById(R.id.restartButton);
        spot1 = (ImageView) findViewById(R.id.space1);
        spot2 = (ImageView) findViewById(R.id.space2);
        spot3 = (ImageView) findViewById(R.id.space3);
        spot4 = (ImageView) findViewById(R.id.space4);
        spot5 = (ImageView) findViewById(R.id.space5);
        spot6 = (ImageView) findViewById(R.id.space6);
        spot7 = (ImageView) findViewById(R.id.space7);
        spot8 = (ImageView) findViewById(R.id.space8);
        spot9 = (ImageView) findViewById(R.id.space9);

        boardImages = new ImageView[10];
        boardImages[1] = spot1;
        boardImages[2] = spot2;
        boardImages[3] = spot3;
        boardImages[4] = spot4;
        boardImages[5] = spot5;
        boardImages[6] = spot6;
        boardImages[7] = spot7;
        boardImages[8] = spot8;
        boardImages[9] = spot9;


        //Add all spots in the board to an ArrayList




        //Fill board
        board = new int[10];

        //Makes it x's turn
        turn = true;


        //set text for the game mode signifier;

        if (gameType) gameTypeSignifier.setText(getString(R.string.standardGameSignifier));
        else gameTypeSignifier.setText(getString(R.string.reverseGameSignifier));


        //Set OnClickListener for all spaces
        for (int i = 1; i < boardImages.length; i++)
        {
            final int j = i;
            boardImages[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    clickSpace(boardImages[j]);
                }
            });
        }

        //Set OnClickListener for restartButton
        restartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, RestartActivity.class);
                intent.putExtra("Game Mode", gameMode);
                startActivity(intent);
            }
        });

    }
    private void clickSpace(ImageView currentSpace)
    {
        int currentSpaceInt = getIntFromSpace(currentSpace);
        if (board[currentSpaceInt] == 0)
        {
            moveCounter++;
            if (isSinglePlayer)
            {
                currentSpace.setImageResource(R.drawable.x);
                board[currentSpaceInt] = 1;
                checkForWinnerX();
                int aiTurn = ai.takeTurn(board);
                boardImages[aiTurn].setImageResource(R.drawable.o);
                board[aiTurn] = 2;
                checkForWinnerO();
                moveCounter++;
            }
            else if(turn) //if it's X's turn
            {
                currentSpace.setImageResource(R.drawable.x);
                board[currentSpaceInt] = 1;
                checkForWinnerX();
            } else {
                currentSpace.setImageResource(R.drawable.o);
                board[currentSpaceInt] = 2;
                checkForWinnerO();
            }
            turn = !turn;
        }
    }
    private void checkForWinnerX()
    {
        boolean case1 = board[1] == 1 && board[2] == 1 && board[3] == 1;
        boolean case2 = board[4] == 1 && board[5] == 1 && board[6] == 1;
        boolean case3 = board[7] == 1 && board[8] == 1 && board[9] == 1;
        boolean case4 = board[1] == 1 && board[4] == 1 && board[7] == 1;
        boolean case5 = board[2] == 1 && board[5] == 1 && board[8] == 1;
        boolean case6 = board[3] == 1 && board[6] == 1 && board[9] == 1;
        boolean case7 = board[1] == 1 && board[5] == 1 && board[9] == 1;
        boolean case8 = board[7] == 1 && board[5] == 1 && board[3] == 1;

        if (case1 || case2 || case3 || case4 || case5 || case6 || case7 || case8)
        {
            Intent intent = new Intent(Game.this, FinalMenu.class);
            if (gameType) gameMode.winner = "x";
            else gameMode.winner = "o";
            intent.putExtra("Game Mode", gameMode);
            startActivity(intent);
        } else if (moveCounter >= 9){
            tieGame();
        }
    }
    private void checkForWinnerO()
    {
        boolean case1 = board[1] == 2 && board[2] == 2 && board[3] == 2;
        boolean case2 = board[4] == 2 && board[5] == 2 && board[6] == 2;
        boolean case3 = board[7] == 2 && board[8] == 2 && board[9] == 2;
        boolean case4 = board[1] == 2 && board[4] == 2 && board[7] == 2;
        boolean case5 = board[2] == 2 && board[5] == 2 && board[8] == 2;
        boolean case6 = board[3] == 2 && board[6] == 2 && board[9] == 2;
        boolean case7 = board[1] == 2 && board[5] == 2 && board[9] == 2;
        boolean case8 = board[7] == 2 && board[5] == 2 && board[3] == 2;

        if (case1 || case2 || case3 || case4 || case5 || case6 || case7 || case8)
        {
            Intent intent = new Intent(Game.this, FinalMenu.class);
            if (gameType) gameMode.winner = "o";
            else gameMode.winner = "x";
            intent.putExtra("Game Mode", gameMode);
            startActivity(intent);
        }
    }
    private void tieGame()
    {
        Intent intent = new Intent(Game.this, FinalMenu.class);
        gameMode.winner = "tie";
        intent.putExtra("Game Mode", gameMode);
        startActivity(intent);
    }
    private int getIntFromSpace(ImageView currentSpace)
    {

        if (currentSpace == spot1) return 1;
        else if (currentSpace == spot2) return 2;
        else if (currentSpace == spot3) return 3;
        else if (currentSpace == spot4) return 4;
        else if (currentSpace == spot5) return 5;
        else if (currentSpace == spot6) return 6;
        else if (currentSpace == spot7) return 7;
        else if (currentSpace == spot8) return 8;
        else if (currentSpace == spot9) return 9;
        else Log.d(Game.class.getSimpleName(), "getIntFromSpace Didn't work right.");
        return 0;

    }
}
