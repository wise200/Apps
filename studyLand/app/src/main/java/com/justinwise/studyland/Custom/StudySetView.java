package com.justinwise.studyland.Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AbsListView;

import com.justinwise.studyland.R;

import java.util.ArrayList;

/**
 * Created by jwise200 on 7/27/2017.
 */

public class StudySetView extends View {

    private String displayText;
    private Paint p;
    private Rect tempRect, bitRect;
    boolean isSelected;
    private Context context;
    private static Bitmap[] highlights = null;
    private boolean isButton;

    public StudySetView(Context newContext, String newText, int height, boolean button) {
        super(newContext);
        context = newContext;
        displayText = newText;
        p = new Paint();
        tempRect = new Rect();
        bitRect = new Rect();
        isSelected = false;
        isButton = button;
        if (highlights == null) {
            highlights = new Bitmap[] {
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_one),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_two),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_three),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_four),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_five),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_six),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_seven),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_eight),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_nine),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_ten),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_eleven),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.highlight_twelve)
            };
        }
        setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, height));
    }

    public boolean isButton() {
        return isButton;
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        //Draw white bg
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),p);

        //Draw lines
        p.setColor(ResourcesCompat.getColor(getResources(), R.color.darkBlue, null));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3);

        canvas.drawLine(0, canvas.getHeight() / 3, canvas.getWidth(), canvas.getHeight() / 3, p);
        canvas.drawLine(0, canvas.getHeight() * 2 / 3, canvas.getWidth(), canvas.getHeight() * 2 / 3, p);
        canvas.drawLine(0, canvas.getHeight() - 3, canvas.getWidth(), canvas.getHeight() - 3, p);

        p.setColor(ResourcesCompat.getColor(getResources(), R.color.darkRed, null));
        canvas.drawLine(canvas.getWidth() / 5, 0, canvas.getWidth() / 5, canvas.getHeight(), p);

        //Draw text
        tempRect.setEmpty();
        p.setTextAlign(Paint.Align.CENTER);
        p.setTextSize(100);
        p.setTypeface(Typeface.SANS_SERIF);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);
        p.getTextBounds(displayText, 0, displayText.length(), tempRect);
        int textY = canvas.getHeight() - tempRect.bottom - canvas.getHeight() / 3;
        canvas.drawText(displayText, canvas.getWidth() /2, textY, p);

        //Draw highlight
        if (isSelected) {
            int x = (canvas.getWidth() - tempRect.right) / 2 - 20;
            Bitmap highlight = getRandomHighlight();
            int radius = tempRect.height();
            while (x < (canvas.getWidth() + tempRect.right) / 2  + 40 - radius) {
                bitRect.set(x,textY-radius,x+radius,textY);
                canvas.drawBitmap(highlight, null, bitRect, p);
                x += radius / 3;
            }
        }
    }
    private Bitmap getRandomHighlight() {
        return highlights[(int) (Math.random()*highlights.length)];
    }

    public void toggle() {
        isSelected = !isSelected;
        invalidate();
    }

    public void set(String newText, int height, boolean isNowButton) {
        isSelected = false;
        displayText = newText;
        isButton = isNowButton;
        setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, height));
    }
}
