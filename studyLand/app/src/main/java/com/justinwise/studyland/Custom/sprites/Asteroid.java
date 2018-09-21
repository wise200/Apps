package com.justinwise.studyland.Custom.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.justinwise.studyland.R;

/**
 * Created by jwise200 on 8/1/2017.
 */

public class Asteroid extends Sprite {
    private Bitmap bm;
    private Point point;
    private int rotation;
    private String text;
    private Point velocity;
    private Matrix m;
    private int rotationSpeed;

    public Asteroid(int maxX, String newText, int approxSpeed, Context context) {
        rotation = 0;
        text = newText;
        velocity = getNewSpeed(approxSpeed);
        m = new Matrix();
        bm = generateBitmap(context);
        point = new Point((int) (Math.random() * (maxX - bm.getWidth() - 80) + 40), -bm.getHeight());
        rotationSpeed = (int) (Math.random() * 3) + 3;
    }

    public void draw(Canvas canvas) {
        m.reset();
        m.postTranslate(point.x, point.y);
        m.postRotate(rotation, point.x + bm.getWidth() /2, point.y + bm.getHeight() / 2);
        canvas.drawBitmap(bm, m, null);
    }

    synchronized public void increment() {
        point.y += velocity.y;
        point.x += velocity.x;
        rotation = (rotation + rotationSpeed) % 360;
    }

    private Point getNewSpeed(double seed) {
        double margin = seed * .3;
        int y = (int) (seed + Math.random() * margin * 2 - margin);
        int x = (int) (Math.random() * 3) - 1;
        return new Point(x, y);
    }

    public boolean outOfBounds(Canvas c) {
        return point.y > c.getHeight() + bm.getHeight();
    }

    private Bitmap generateBitmap(Context context) {
        Bitmap src = BitmapFactory.decodeResource(context.getResources(), R.drawable.asteroid_one);
        Bitmap finalBitmap = src.copy(src.getConfig(), true);
        finalBitmap.setConfig(Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(finalBitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.WHITE);
        p.setTextSize((int) (src.getHeight() * .8));
        p.setTextAlign(Paint.Align.CENTER);
        Rect bounds = new Rect();
        p.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, canvas.getWidth() / 2, (canvas.getHeight() + bounds.height()) / 2 , p);
        return Bitmap.createScaledBitmap(finalBitmap, finalBitmap.getWidth() / 2, finalBitmap.getHeight() / 2, false);
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
