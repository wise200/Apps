package Custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;

import com.justinwise.ballbouncer.R;

/**
 * Created by jwise200 on 1/31/2017.
 */

public class Ball implements Parcelable {

    private float x, y;
    private int color;
    private int width, height;

    public Ball(float newX, float newY, int newHeight, int newWidth, int r, int g, int b)
    {
        x = newX;
        y = newY;
        height = newHeight;
        width = newWidth;
        color = Color.rgb(r,g,b);
    }
    public View addView(Context context, RelativeLayout layout)
    {
        View view = new View(context);
        view.setX(x);
        view.setY(y);
        GradientDrawable shape = (GradientDrawable) context.getResources().getDrawable(R.drawable.ball);
        shape.setColor(color);
        view.setBackground(shape);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        layout.addView(view, params);
        return view;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(this.y);
        dest.writeInt(this.color);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected Ball(Parcel in) {
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.color = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<Ball> CREATOR = new Creator<Ball>() {
        @Override
        public Ball createFromParcel(Parcel source) {
            return new Ball(source);
        }

        @Override
        public Ball[] newArray(int size) {
            return new Ball[size];
        }
    };
}
