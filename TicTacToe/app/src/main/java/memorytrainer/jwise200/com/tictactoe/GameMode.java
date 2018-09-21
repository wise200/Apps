package memorytrainer.jwise200.com.tictactoe;

import java.io.Serializable;


public class GameMode implements Serializable{
    public boolean isStandard;
    public String winner;
    public boolean isSinglePlayer;
    public GameMode(boolean willBeStandard, boolean willBeSinglePlayer)
    {
        isStandard = willBeStandard;
        isSinglePlayer = willBeSinglePlayer;
        winner = "No One Yet";
    }



}
