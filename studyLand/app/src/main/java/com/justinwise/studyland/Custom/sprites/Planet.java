package com.justinwise.studyland.Custom.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.justinwise.studyland.R;

/**
 * Created by justi on 8/17/2017.
 */

public class Planet{
    private Point point;
    private Bitmap bm;
    private int velocity;

    public Planet(int maxX, Context context) {
        bm = BitmapFactory.decodeResource(context.getResources(), getID());
        if (bm.getHeight() > 300)
            bm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, false);
        point = new Point((int) (Math.random()* (maxX - bm.getWidth())), -bm.getHeight());
        velocity = (int) (Math.random() * 6) + 4;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm, point.x, point.y, null);
    }

    public void increment() {
        point.y += velocity;
    }

    public boolean outOfBounds(Canvas c) {
        return point.y > c.getHeight() + bm.getHeight();
    }

    private int getID() {
        double rand = Math.random();
        if (rand < 1 / 3.0)
            return R.drawable.p2;
        if (rand < 2 / 3.0)
            return R.drawable.p3;
        return R.drawable.p4;
    }
}
