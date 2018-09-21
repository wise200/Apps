package com.justinwise.studyland.Custom.sprites;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;

/**
 * Created by justi on 8/17/2017.
 */

public abstract class Sprite {
    public abstract Rect bounds(Rect seed);
    public abstract Bitmap bitmap();
    private static Rect seed1 = new Rect(), seed2 = new Rect(), seed3 = new Rect();
    public boolean collidesWith(Sprite other) {
        Rect thisRect = bounds(seed1);
        Rect otherRect = other.bounds(seed2);
        Rect intersect = seed3;
        intersect.set(thisRect);

        if (intersect.intersect(otherRect))
            for (int r = intersect.top; r < intersect.bottom; r++)
                for (int c = intersect.left; c < intersect.right; c++)
                    if (bitmap().getPixel(c - thisRect.left, r - thisRect.top) != Color.TRANSPARENT)
                        if (other.bitmap().getPixel(c - otherRect.left, r - otherRect.top) != Color.TRANSPARENT)
                            return true;

        return false;
    }
}
