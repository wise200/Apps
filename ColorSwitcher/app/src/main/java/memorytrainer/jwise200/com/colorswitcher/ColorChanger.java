package memorytrainer.jwise200.com.colorswitcher;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jwise200 on 8/20/2016.
 */
public class ColorChanger {
    JColor firstColor;
    JColor secondColor;
    View view;
    int millis;
    JColor currentColor;
    double rIncrement, gIncrement, bIncrement;
    TextView progress1, progress2, progress3;

    public ColorChanger(JColor newFirstColor, JColor newSecondColor, View newView, int newMillis, TextView newprogress1, TextView newprogress2, TextView newprogress3)
    {
        firstColor = newFirstColor;
        secondColor = newSecondColor;
        view = newView;
        millis  = newMillis;
        progress1 = newprogress1;
        progress2 = newprogress2;
        progress3 = newprogress3;
    }

    public void flipFlopColors()
    {
        int rDiff = secondColor.getR() - firstColor.getR();
        int gDiff = secondColor.getG() - firstColor.getG();
        int bDiff = secondColor.getB() - firstColor.getB();

        int numChanges = millis / 50;

        rIncrement = (int) Math.ceil(rDiff / (double) numChanges);
        gIncrement = (int) Math.ceil(gDiff / (double) numChanges);
        bIncrement = (int) Math.ceil(bDiff / (double) numChanges);

        flipColor();
    }

    private void flipColor()
    {
        currentColor = firstColor;
        new CountDownTimer(millis, 50)
        {
            @Override
            public void onTick(long millisUntilFinished) {

                if (rIncrement < 0) {
                    if (currentColor.getR() >= 0 - rIncrement) currentColor.setR((int) Math.ceil(currentColor.getR() + rIncrement));
                } else if (currentColor.getR() <= 255 - rIncrement) currentColor.setR((int) Math.ceil(currentColor.getR() + rIncrement));

                if (gIncrement < 0) {
                    if (currentColor.getG() >= 0 - gIncrement) currentColor.setG((int) Math.ceil(currentColor.getG() + gIncrement));
                } else if (currentColor.getG() <= 255 - gIncrement) currentColor.setG((int) Math.ceil(currentColor.getG() + gIncrement));

                if (bIncrement < 0) {
                    if (currentColor.getB() >= 0 - bIncrement) currentColor.setB((int) Math.ceil(currentColor.getB() + bIncrement));
                } else if (currentColor.getB() <= 255 - bIncrement) currentColor.setB((int) Math.ceil(currentColor.getB() + bIncrement));

                view.setBackgroundColor(Color.parseColor(currentColor.getHex()));
                progress1.setHeight(currentColor.getR());
                progress2.setHeight(currentColor.getG());
                progress3.setHeight(currentColor.getB());

            }

            @Override
            public void onFinish() {
                rIncrement = 0 - rIncrement;
                gIncrement = 0 - gIncrement;
                bIncrement = 0 - bIncrement;
                flopColor();
            }
        }.start();
    }

    private void flopColor()
    {
        currentColor = secondColor;

        new CountDownTimer(millis, 50)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                if (rIncrement < 0) {
                    if (currentColor.getR() >= 0 - rIncrement) currentColor.setR((int) Math.ceil(currentColor.getR() + rIncrement));
                } else if (currentColor.getR() <= 255 - rIncrement) currentColor.setR((int) Math.ceil(currentColor.getR() + rIncrement));

                if (gIncrement < 0) {
                    if (currentColor.getG() >= 0 - gIncrement) currentColor.setG((int) Math.ceil(currentColor.getG() + gIncrement));
                } else if (currentColor.getG() <= 255 - gIncrement) currentColor.setG((int) Math.ceil(currentColor.getG() + gIncrement));

                if (bIncrement < 0) {
                    if (currentColor.getB() >= 0 - bIncrement) currentColor.setB((int) Math.ceil(currentColor.getB() + bIncrement));
                } else if (currentColor.getB() <= 255 - bIncrement) currentColor.setB((int) Math.ceil(currentColor.getB() + bIncrement));

                view.setBackgroundColor(Color.parseColor(currentColor.getHex()));
            }

            @Override
            public void onFinish() {
                rIncrement = 0 - rIncrement;
                gIncrement = 0 - gIncrement;
                bIncrement = 0 - bIncrement;
                flipColor();
            }
        }.start();
    }
}
