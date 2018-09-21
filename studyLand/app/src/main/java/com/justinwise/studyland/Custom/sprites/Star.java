package com.justinwise.studyland.Custom.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jwise200 on 8/1/2017.
 */

public class Star {
    private int x;
    private int y;
    private int alpha;
    private int gap;
    private int speed;
    private int canvasHeight;

    public Star(int x, int y, int speed, int canvasHeight) {
        this.x = x;
        this.y = y;
        this.canvasHeight = canvasHeight;
        this.speed = speed;
        alpha = (int) (Math.random() * 150) + 100;
        gap = 1;
        if (Math.random() > .5)
            gap *= -1;
    }

    public void draw(Canvas canvas, Paint p) {
        canvas.drawCircle(x,y,5,p);
    }

    public int alpha() {
        return alpha;
    }

    public void increment() {
        if (alpha > 250 || alpha < 100)
            gap *= -1;
        alpha += gap;
        y = (y + speed) % canvasHeight;
    }
}

