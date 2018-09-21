package com.justinwise.chesstimer.customclasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jwise200 on 8/20/2016.
 */
public class JColor implements Parcelable {
    int r, g, b;

    public JColor(int newR, int newG, int newB)
    {
        checkParam(newR);
        checkParam(newG);
        checkParam(newB);
        r = newR;
        g = newG;
        b = newB;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        checkParam(r);
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        checkParam(g);
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        checkParam(b);
        this.b = b;
    }

    private void checkParam(int param)
    {
        if (param < 0 || param > 255)
            throw new IllegalArgumentException("That's not a color!");
    }

    public String toString()
    {
        return String.format("#%02X%02X%02X", r, g, b);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.r);
        dest.writeInt(this.g);
        dest.writeInt(this.b);
    }

    protected JColor(Parcel in) {
        this.r = in.readInt();
        this.g = in.readInt();
        this.b = in.readInt();
    }

    public static final Parcelable.Creator<JColor> CREATOR = new Parcelable.Creator<JColor>() {
        @Override
        public JColor createFromParcel(Parcel source) {
            return new JColor(source);
        }

        @Override
        public JColor[] newArray(int size) {
            return new JColor[size];
        }
    };
}
