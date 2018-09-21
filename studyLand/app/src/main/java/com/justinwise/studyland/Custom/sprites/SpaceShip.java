package com.justinwise.studyland.Custom.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.justinwise.studyland.R;

import java.util.ArrayList;

/**
 * Created by justi on 8/16/2017.
 */

public class SpaceShip {
    private Point p;
    private Bitmap bm;
    private int velocity;
    private int maxX;
    private Context context;

    public SpaceShip(int middleX, int bottomY, Context context, int maxX) {
        this.context = context;
        bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        bm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, false);
        p = new Point(middleX - (bm.getWidth() / 2), bottomY - bm.getHeight() - 20);
        velocity = 14;
        this.maxX = maxX;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm, p.x, p.y, null);
    }

    public void increment() {
        p.x += velocity;
        if (p.x > maxX - bm.getWidth() || p.x < 0)
            velocity *= -1;
    }

    public void addBullets(ArrayList<Bullet> list) {
        list.add(new Bullet(p.x + bm.getWidth() / 2 - 35, p.y + 20, 40
                , context));
    }

}
