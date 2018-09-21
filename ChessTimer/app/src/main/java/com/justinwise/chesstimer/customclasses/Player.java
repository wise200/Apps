package com.justinwise.chesstimer.customclasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

/**
 * Created by jwise200 on 6/28/2017.
 */

public class Player implements Parcelable {
    private String name;
    private long millis;
    private JColor color;
    private long originalMillis;

    public Player(String newName, int newSecs, JColor newColor) {
        name = newName;
        millis = newSecs * 1000;
        originalMillis = newSecs * 1000;
        color = newColor;
        checkTime();
    }

    public void changeSeconds(int diff) {
        millis += diff * 1000;
        checkTime();
    }

    public void setMillis(long diff) {
        millis = diff;
        checkTime();
    }


    public String getName() {
        return name;
    }
    public JColor getColor() {
        return color;
    }


    public String getTime() {
        if (getSeconds() / 3600.0 < 1)
            return String.format(Locale.US, "%d:%02d.%d", getSeconds() / 60, getSeconds() % 60, millis / 100 % 10);
        return String.format(Locale.US, "%d:%02d:%02d", getSeconds() / 3600, getSeconds() % 3600 / 60, getSeconds() % 60);
    }

    public int getSeconds() {
        return (int) (millis / 1000);
    }

    public long getMillis() {
        return millis;
    }

    private void checkTime() {
        if (millis < 0 || getSeconds() > 24 * 3600)
            throw new IllegalArgumentException("Not a Valid Time");
    }

    public void reset() {
        millis = originalMillis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.millis);
        dest.writeParcelable(this.color, flags);
        dest.writeLong(this.originalMillis);
    }

    protected Player(Parcel in) {
        this.name = in.readString();
        this.millis = in.readLong();
        this.color = in.readParcelable(JColor.class.getClassLoader());
        this.originalMillis = in.readLong();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
