package Custom;

/**
 * Created by jwise200 on 12/25/2016.
 */

public enum Adjacent {
    TOP (-1, 0), BOTTOM (1, 0), LEFT (0, -1), RIGHT (0, 1);

    private int rowDiff, colDiff;
    private Adjacent opposite;

    static {
        TOP.opposite = BOTTOM;
        BOTTOM.opposite = TOP;
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    public Adjacent getOpposite()
    {
        return opposite;
    }

    Adjacent(int newRowDiff, int newColDiff)
    {
        rowDiff = newRowDiff;
        colDiff = newColDiff;
    }

    public int getRowDiff()
    {
        return rowDiff;
    }

    public int getColDiff()
    {
        return colDiff;
    }
}
