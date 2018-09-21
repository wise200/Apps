package com.justinwise.studyland.Custom.sprites;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.justinwise.studyland.R;

/**
 * Created by justi on 8/17/2017.
 */

public class Bullet extends Sprite {

    private Point point;
    private int velocity;
    private static int color;
    private static Bitmap bm;

    public Bullet(int x, int y, int velocity, Context c) {
        point = new Point(x, y);
        this.velocity = velocity;
        if (bm == null) {
            bm = BitmapFactory.decodeResource(c.getResources(), R.drawable.bullet);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm, point.x, point.y, null);
    }

    public void increment() {
        point.y -= velocity;
    }

    public boolean outOfBounds() {
        return point.y < 0 - bm.getHeight();
    }

    @Override
    public Rect bounds(Rect seed) {
        seed.set(point.x, point.y, point.x + bm.getWidth(), point.y + bm.getHeight());
        return seed;
    }

    @Override
    public Bitmap bitmap() {
        return bm;
    }
}
