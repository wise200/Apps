package Custom;

/**
 * Created by jwise200 on 12/24/2016.
 */

public class Cell {
    public boolean visited;
    public boolean topWall, bottomWall, leftWall, rightWall;

    public Cell()
    {
        visited = false;
        topWall = true;
        bottomWall = true;
        leftWall = true;
        rightWall = true;
    }

    public void removeWall(Adjacent wall)
    {
        if (wall.equals(Adjacent.TOP))
            topWall = false;
        else if (wall.equals(Adjacent.RIGHT))
            rightWall = false;
        else if (wall.equals(Adjacent.BOTTOM))
            bottomWall = false;
        else if (wall.equals(Adjacent.LEFT))
            leftWall = false;
        else throw new IllegalArgumentException();
    }
}
