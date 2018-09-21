package memorytrainer.jwise200.com.tictactoe;

public class Row {
    private String owner; //"empty", "x", "o", or "both"
    private int[] cells;
    private boolean isDoubled;

    public Row(int[] myCells)
    {
        owner = "empty";
        cells = myCells;
        isDoubled = false;
    }

    public String getOwner() {
        return owner;
    }

    public int[] getCells() {
        return cells;
    }

    public boolean isDoubled() {
        return isDoubled;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDoubled(boolean doubled) {
        isDoubled = doubled;
    }
}
