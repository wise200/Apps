package com.justinwise.studyland.Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.justinwise.studyland.Custom.sprites.Asteroid;
import com.justinwise.studyland.Custom.sprites.Bullet;
import com.justinwise.studyland.Custom.sprites.Planet;
import com.justinwise.studyland.Custom.sprites.SpaceShip;
import com.justinwise.studyland.Custom.sprites.Star;

import java.util.ArrayList;

/**
 * Created by jwise200 on 7/31/2017.
 */

public class AstroBlitzView extends View {

    private Context context;
    private Paint p;
    private ArrayList<Star> stars;
    private static final int starCount = 30;
    private ArrayList<Asteroid> asteroids;
    private SpaceShip ship;
    private ArrayList<Bullet> bullets;
    private ArrayList<Boolean> deletableAsteroids;
    private ArrayList<Planet> planets;
    private int width;


    public AstroBlitzView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        stars = null;
        asteroids = new ArrayList<Asteroid>();
        ship = null;
        this.context = context;
        bullets = new ArrayList<Bullet>();
        planets = new ArrayList<Planet>();
        deletableAsteroids = new ArrayList<Boolean>();
    }

    private void addStars(int w, int h) {
        stars = new ArrayList<Star>();
        for (int i = 0; i < starCount; i++) {
            int x = (int) (Math.random() * w) + 1;
            int y = (int) (Math.random() * h) + 1;
            stars.add(new Star(x,y,10,h));
        }
    }

    synchronized public void addAsteroid() {
        asteroids.add(new Asteroid(width, "A", 10, context));
        deletableAsteroids.add(false);
    }

    synchronized public void shoot() {
        ship.addBullets(bullets);
    }

    synchronized public void addPlanet() {
        planets.add(new Planet(width, context));
    }

    private boolean collisionCheck() {
        return true;
    }

    @Override
    synchronized protected void onDraw(Canvas canvas) {
        if (stars == null) {
            addStars(canvas.getWidth(), canvas.getHeight());
            ship = new SpaceShip(canvas.getWidth() / 2, canvas.getHeight(), context, canvas.getWidth());
            width = canvas.getWidth();
        }

        //Draw bg
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),p);
        //Draw stars
        p.setColor(Color.WHITE);
        for (Star star : stars) {
            p.setAlpha(star.alpha());
            star.draw(canvas, p);
            star.increment();
        }

        //Draw Planets
        for (int i = planets.size() - 1; i >= 0; i--) {
            Planet planet = planets.get(i);
            planet.draw(canvas);
            planet.increment();
            if (planet.outOfBounds(canvas))
                planets.remove(i);
        }


        //Draw Asteroids
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Asteroid ast = asteroids.get(i);
            ast.draw(canvas);
            ast.increment();
            if (ast.outOfBounds(canvas))
                asteroids.remove(i);
        }

        //Draw bullets
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.draw(canvas);
            bullet.increment();
            if (bullet.outOfBounds())
                bullets.remove(i);
        }

        //Draw ship
        ship.draw(canvas);
        ship.increment();


        //Collision Check
        for (int i = asteroids.size() - 1; i >= 0; i--)
            for (int j = bullets.size() - 1; j >= 0; j--)
                if (asteroids.get(i).collidesWith(bullets.get(j))) {
                    deletableAsteroids.set(i, true);
                    bullets.remove(j);
                }
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            if (deletableAsteroids.get(i)) {
                asteroids.remove(i);
                deletableAsteroids.remove(i);
            }
        }
    }
}
