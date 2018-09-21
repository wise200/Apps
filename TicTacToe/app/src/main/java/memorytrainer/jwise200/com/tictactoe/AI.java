package memorytrainer.jwise200.com.tictactoe;


import java.util.Arrays;
import java.util.PriorityQueue;

public class AI {

    private int cell;
    private Row r123 = new Row(new int[] {1,2,3});
    private Row r456 = new Row(new int[] {4,5,6});
    private Row r789 = new Row(new int[] {7,8,9});
    private Row r147 = new Row(new int[] {1,4,7});
    private Row r258 = new Row(new int[] {2,5,8});
    private Row r369 = new Row(new int[] {3,6,9});
    private Row r159 = new Row(new int[] {1,5,9});
    private Row r357 = new Row(new int[] {3,5,7});

    private Row[] allRows = new Row[] {r123, r456, r789, r147, r258, r369, r159, r357};

    private int[] board= new int[10];

    private Row[] rowsForCell1 = new Row[] {r123, r147,r159};
    private Row[] rowsForCell2 = new Row[] {r123, r258};
    private Row[] rowsForCell3 = new Row[] {r123, r369,r357};
    private Row[] rowsForCell4 = new Row[] {r456, r147};
    private Row[] rowsForCell5 = new Row[] {r456, r258,r159, r357};
    private Row[] rowsForCell6 = new Row[] {r456, r369};
    private Row[] rowsForCell7 = new Row[] {r789, r147};
    private Row[] rowsForCell8 = new Row[] {r789, r258};
    private Row[] rowsForCell9 = new Row[] {r789, r369,r159};

    private Row[] dummy;

    private Row[][] intToRows = new Row[][]
    {
            dummy,
            rowsForCell1,
            rowsForCell2,
            rowsForCell3,
            rowsForCell4,
            rowsForCell5,
            rowsForCell6,
            rowsForCell7,
            rowsForCell8,
            rowsForCell9,
    };

    private Row doubledRow;

    public int takeTurn(int[] newBoard)
    {
        int changedCell = compareBoards(newBoard);
        board = newBoard.clone();

        adjustRowsPreTurn(changedCell);

        if (iHaveMatchPoint())
            cell = getEmptyCell();
        else if (theyHaveMatchPoint())
            cell = getEmptyCell();
        else
            cell = attack();

        adjustRowsPostTurn(cell);
        return cell;
    }

    private  int compareBoards(int[] newBoard)
    {
        for (int i = 1; i <= 9; i++)
        {
            if (newBoard[i] != board[i])
                return i;
        }
        return -1;
    }

    private void adjustRowsPreTurn(int changedCell)
    {
        Row[] changedCellRows = intToRows[changedCell];
        for (Row currentRow : changedCellRows)
        {
            if (currentRow.getOwner().equals("empty"))
            {
                currentRow.setOwner("human");
            } else if (currentRow.getOwner().equals("human"))
            {
                currentRow.setDoubled(true);
            }
            else if (currentRow.getOwner().equals("ai")) {
                currentRow.setOwner("both");
                if (currentRow.isDoubled())
                    currentRow.setDoubled(false);
            }
        }
    }
    private void adjustRowsPostTurn(int cellToChange)
    {
        Row[] changedCellRows = intToRows[cellToChange];
        for (Row currentRow : changedCellRows)
        {
            if (currentRow.getOwner().equals("empty"))
                currentRow.setOwner("ai");
            else if (currentRow.getOwner().equals("human")) {
                if (currentRow.isDoubled())
                    currentRow.setDoubled(false);
                currentRow.setOwner("both");
            }
            else if (currentRow.getOwner().equals("ai"))
                currentRow.setDoubled(true);
        }
    }

    private boolean iHaveMatchPoint()
    {
        for (Row currentRow : allRows)
        {
            if (currentRow.isDoubled())
            {
                if (currentRow.getOwner().equals("ai"))
                {
                    doubledRow = currentRow;
                    return true;
                }
            }
        }
        return false;
    }

    private int getEmptyCell()
    {
        for (int currentCell : doubledRow.getCells())
        {
            if (board[currentCell] == 0)
                return currentCell;
        }
        return -1;
    }

    private boolean theyHaveMatchPoint()
    {
        for (Row currentRow : allRows)
        {
            if (currentRow.isDoubled())
            {
                if (currentRow.getOwner().equals("human"))
                {
                    doubledRow = currentRow;
                    return true;
                }
            }
        }
        return false;
    }

    private int attack()
    {
        int dummyCounter = -1000, counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0;
        int counter5 = 0, counter6 = 0, counter7 = 0, counter8 = 0, counter9 = 0;
        int[] counters = new int[]
        {
                dummyCounter, counter1, counter2, counter3, counter4, counter5, counter6, counter7, counter8, counter9
        };
        int highestCounterVal;
        for (int currentCell = 1; currentCell <= 9; currentCell++)
        {
            for (Row currentRow : intToRows[currentCell])
            {
                if (currentRow.getOwner().equals("ai"))
                    counters[currentCell] += 2;
                if (currentRow.getOwner().equals("empty"))
                    counters[currentCell] ++;
                if (currentRow.getOwner().equals("human"))
                    counters[currentCell] --;
                if (currentRow.getOwner().equals("both"))
                    counters[currentCell] -= 2;
            }
        }
        int[] counterBackup = counters.clone();
        Arrays.sort(counters);
        highestCounterVal = counters[9];
        for (int i = 1; i <= 9; i++)
        {
            if (counterBackup[i] == highestCounterVal)
                return i;
        }
        return -1;
    }

}
