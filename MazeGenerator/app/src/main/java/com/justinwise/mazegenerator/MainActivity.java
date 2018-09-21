package com.justinwise.mazegenerator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Custom.*;


public class MainActivity extends AppCompatActivity {
    private Cell[][] cells;
    MyRelativeLayout layout;
    boolean isMazeGenerated;
    int red, blue, green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int rows, cols;
        //Determine Maze Dimensions
        Intent intent = getIntent();
        if (intent.hasExtra("rows") && intent.hasExtra("cols"))
        {
            Bundle extras = intent.getExtras();
            rows = extras.getInt("rows");
            cols = extras.getInt("cols");
        } else {
            rows = 10;
            cols = 10;
        }

        if (intent.hasExtra("red") && intent.hasExtra("green") && intent.hasExtra("blue"))
        {
            Bundle extras = intent.getExtras();
            red = extras.getInt("red");
            green = extras.getInt("green");
            blue = extras.getInt("blue");
        } else {
            red = 0;
            green = 255;
            blue = 0;
        }

        //declare and fill cells array
        cells = new Cell[rows][cols];
        fillCells();
        isMazeGenerated = false;

        setContentView(R.layout.activity_main);

        layout = (MyRelativeLayout) findViewById(R.id.activity_main);
        layout.setCells(cells);
        layout.setColor(red, green, blue);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMazeGenerated)
                    generateMaze(0, 0);
                else
                    fillCells();
                layout.invalidate();
                isMazeGenerated = !isMazeGenerated;
            }
        });
    }


    private void fillCells()
    {
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells[0].length; c++)
                cells[r][c] = new Cell();
    }

    private void generateMaze(int row, int col)
    {
        //Ensure no cell is processed twice
        if (cells[row][col].visited)
            return;
        layout.highlightCellAndRefresh(row, col);
        cells[row][col].visited = true;
        ArrayList<Adjacent> neighbors = shuffleList(findUnvisitedAdj(row, col));
        for (Adjacent adjacent : neighbors)
        {
            if (!cells[row + adjacent.getRowDiff()][col + adjacent.getColDiff()].visited) {
                removeWall(row, col, adjacent);
                int neighborRow = row + adjacent.getRowDiff();
                int neighborCol = col + adjacent.getColDiff();
                removeWall(neighborRow, neighborCol, adjacent.getOpposite());
                generateMaze(neighborRow, neighborCol);
            }
        }
    }

    private void removeWall(int row, int col, Adjacent side)
    {
        cells[row][col].removeWall(side);
    }

    private ArrayList<Adjacent> findUnvisitedAdj(int row, int col)
    {
        ArrayList<Adjacent> adjacents = new ArrayList<Adjacent>();
        if (row != 0 && !cells[row - 1][col].visited)
            adjacents.add(Adjacent.TOP);
        if (row != cells.length - 1 && !cells[row + 1][col].visited)
            adjacents.add(Adjacent.BOTTOM);
        if (col != 0 && !cells[row][col - 1].visited)
            adjacents.add(Adjacent.LEFT);
        if (col != cells[0].length - 1 && !cells[row][col + 1].visited)
            adjacents.add(Adjacent.RIGHT);
        return adjacents;
    }

    private ArrayList<Adjacent> shuffleList(ArrayList<Adjacent> list)
    {
        for (int i = list.size() - 1; i >= 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Adjacent temp = list.get(i);
            list.set(i, list.get(rand));
            list.set(rand, temp);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.changeDimensions:
                Intent intent1 = new Intent(this, EditDimensionsActivity.class);
                intent1.putExtra("rows", cells.length);
                intent1.putExtra("cols", cells[0].length);
                intent1.putExtra("red", red);
                intent1.putExtra("green", green);
                intent1.putExtra("blue", blue);
                startActivity(intent1);
                return true;
            case R.id.changeColor:
                Intent intent2 = new Intent(this, ColorPickerActivity.class);
                intent2.putExtra("red", red);
                intent2.putExtra("green", green);
                intent2.putExtra("blue", blue);
                intent2.putExtra("rows", cells.length);
                intent2.putExtra("cols", cells[0].length);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
