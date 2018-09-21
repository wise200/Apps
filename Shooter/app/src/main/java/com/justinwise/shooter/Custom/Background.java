package com.justinwise.shooter.Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Matrix;
import com.justinwise.shooter.R;
import android.graphics.Rect;
import java.util.ArrayList;

/**
 * Created by jwise200 on 7/13/2017.
 */

public class Background extends View {

    private Paint p;
    private ArrayList<Star> stars;
    private int starCount;
    private Point saucerPoint;
    private Point asteroidPoint;
    private Bitmap saucer;
    private Bitmap asteroid;
    private Matrix m;
    private int rotation;
    private Rect ast, ufo, intersect;
    private Point lastCollision;
    private boolean wasCollision;

    public Background(Context context, AttributeSet attrs) {
        super(context, attrs);
        starCount = 25;
        p = new Paint();
        stars = null;

        asteroidPoint = new Point(-1,-1);
        saucerPoint = new Point(-1,-1);
        asteroid = BitmapFactory.decodeResource(getResources(), R.drawable.asteroid);
        saucer = BitmapFactory.decodeResource(getResources(), R.drawable.ufo);
        m = new Matrix();
        rotation = 0;
        ufo = new Rect(0,0,0,0);
        ast = new Rect(ufo);
        intersect = new Rect(ufo);
        resetCollisions();
    }

    private void addStars(int w, int h) {
        stars = new ArrayList<Star>();
        for (int i = 0; i < starCount; i++) {
            int x = (int) (Math.random() * w) + 1;
            int y = (int) (Math.random() * h) + 1;
            stars.add(new Star(x,y));
        }
    }


     synchronized public void onDraw(Canvas canvas) {
        if (stars == null)
            addStars(canvas.getWidth(), canvas.getHeight());

        //Draw black bg
        p.setColor(Color.BLACK);
        p.setStrokeWidth(1);
        canvas.drawRect(0,0,getWidth(),getHeight(),p);
        
        //Draw Stars
        p.setColor(Color.WHITE);
        p.setStrokeWidth(10);
        for (Star star : stars) {
            p.setAlpha(star.alpha());
            canvas.drawPoint(star.x(), star.y(), p);
            star.increment();
        }

        //Draw asteroid and star
        if (asteroidPoint.x >= 0) {
            m.reset();
            m.postTranslate(asteroidPoint.x, asteroidPoint.y);
            m.postRotate(rotation, asteroidPoint.x + asteroid.getWidth() / 2, asteroidPoint.y + asteroid.getHeight() / 2);
            canvas.drawBitmap(asteroid, m, null);
            if (rotation < 355)
                rotation += 5;
            else
                rotation = 0;
        }
        if (saucerPoint.x >= 0)
            canvas.drawBitmap(saucer, saucerPoint.x, saucerPoint.y, null);

        //Scan for collisions
        wasCollision = collisionCheck();
    }

    public boolean collisionCheck() {
        if (asteroidPoint.x < 0 && asteroidPoint.y < 0 && saucerPoint.x < 0 && saucerPoint.y < 0)
            return false;
        ast.set(asteroidPoint.x, asteroidPoint.y,
                asteroidPoint.x + asteroid.getWidth(),
                asteroidPoint.y + asteroid.getHeight());
        ufo.set(saucerPoint.x, saucerPoint.y,
                saucerPoint.x + saucer.getWidth(),
                saucerPoint.y + saucer.getHeight());
        intersect.set(asteroidPoint.x, asteroidPoint.y,
                asteroidPoint.x + asteroid.getWidth(),
                asteroidPoint.y + asteroid.getHeight());
        if (intersect.intersect(ufo)) {
            for (int r = intersect.top; r < intersect.bottom; r++)
                for (int c = intersect.left; c < intersect.right; c++)
                    if (asteroid.getPixel(c-ast.left, r-ast.top) != Color.TRANSPARENT)
                        if (saucer.getPixel(c-ufo.left, r-ufo.top) != Color.TRANSPARENT) {
                            lastCollision.x = c;
                            lastCollision.y = r;
                            return true;
                        }
        }
        return false;
    }

    synchronized public int getAsteroidX() {
        return asteroidPoint.x;
    }

    synchronized public int getAsteroidY() {
        return asteroidPoint.y;
    }

    synchronized public void setAsteroidPoint(Point point) {
        this.asteroidPoint = new Point(point.x, point.y);
    }

    synchronized public int getSaucerX() {
        return saucerPoint.x;
    }

    synchronized public int getSaucerY() {
        return saucerPoint.y;
    }

     synchronized public void setSaucerPoint(Point point) {
        this.saucerPoint = new Point(point.x,point.y);
    }

    synchronized public int getAsteroidHeight() {
        return asteroid.getHeight();
    }

    synchronized public int getAsteroidWidth() {
        return asteroid.getWidth();
    }

    synchronized public int getSaucerHeight() {
        return saucer.getHeight();
    }

    synchronized public int getSaucerWidth() {
        return saucer.getWidth();
    }
    synchronized public boolean wasCollision() {
        return wasCollision;
    }

    synchronized public Point getLastCollision() {
        return lastCollision;
    }

    synchronized public void resetCollisions() {
        lastCollision = new Point(-1,-1);
        wasCollision = false;
    }
}
