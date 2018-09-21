package Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by jwise200 on 1/6/2017.
 */

public class MyRelativeLayout extends RelativeLayout {
    Paint paint;
    Cell[][] cells;
    int currentRow, currentCol;

    public MyRelativeLayout(Context context) {
        super(context);
        generalConstructor();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalConstructor();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        generalConstructor();
    }

    private void generalConstructor() {
        paint = new Paint();
        paint.setColor(Color.rgb(0, 255, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        currentRow = 0;
        currentCol = 0;
    }

    public void setCells(Cell[][] newCells)
    {
        cells = newCells;
    }

    public void setColor(int red, int green, int blue)
    {
        paint.setColor(Color.rgb(red, green, blue));
    }

    public void highlightCellAndRefresh(int row, int col)
    {
        currentRow = row;
        currentCol = col;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //Calculations
        int sizeBuffer = 10;
        int maxHeight = (int) (canvas.getHeight() - sizeBuffer) / cells.length;
        int maxWidth = (int) (canvas.getWidth() - sizeBuffer) / cells[0].length;
        float length = maxHeight < maxWidth? maxHeight : maxWidth;
        float mazeHeight = cells.length * length;
        float mazeWidth = cells[0].length * length;
        float top = (canvas.getHeight() - mazeHeight) / 2;
        float left = (canvas.getWidth() - mazeWidth) / 2;

        //draw walls
        canvas.drawLine(left, top, left, top + cells.length * length, paint);
        canvas.drawLine(left, top, left + cells[0].length * length, top, paint);
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[0].length; c++) {
                if (cells[r][c].bottomWall)
                    canvas.drawLine(left + c * length, top + (r + 1) * length, left + (c + 1) * length, top + (r + 1) * length, paint);
                if (cells[r][c].rightWall)
                    canvas.drawLine(left + (c + 1) * length, top + r * length, left + (c + 1) * length, top + (r + 1) * length, paint);
            }
        }

        //highlight current cell
        /*paint.setColor(Color.rgb(0, 0, 255));
        paint.setStyle(Paint.Style.FILL);
        int buffer = 2;
        float rectLeft = left + currentCol * length + buffer;
        float rectTop = top + currentRow * length + buffer;
        float rectRight = left + (currentCol + 1) * length - buffer;
        float rectBottom = top + (currentRow + 1) * length - buffer;
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint);
        paint.setColor(Color.rgb(0, 255, 0));
        paint.setStyle(Paint.Style.STROKE); */
    }
}
