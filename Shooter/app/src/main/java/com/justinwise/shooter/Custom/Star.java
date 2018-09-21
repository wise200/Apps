package com.justinwise.shooter.Custom;

/**
 * Created by jwise200 on 7/17/2017.
 */

public class Star {
    private int x;
    private int y;
    private int alpha;
    private int gap;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
        alpha = (int) (Math.random() * 150) + 100;
        gap = 1;
        if (Math.random() > .5)
            gap *= -1;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int alpha() {
        return alpha;
    }

    public void increment() {
        if (alpha > 250 || alpha < 100)
            gap *= -1;
        alpha += gap;
    }
}
