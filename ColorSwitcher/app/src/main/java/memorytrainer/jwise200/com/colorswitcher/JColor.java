package memorytrainer.jwise200.com.colorswitcher;

/**
 * Created by jwise200 on 8/20/2016.
 */
public class JColor {
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

    public String getHex()
    {
        //return String.format("#%02h%02h%02h", r, g, b);

	    String hex = "#";

        if (r < 16) hex = hex + "0" + Integer.toString(r, 16);
        else hex = hex + Integer.toString(r, 16);

        if (g < 16) hex = hex + "0" + Integer.toString(g, 16);
        else hex = hex + Integer.toString(g, 16);

        if (b < 16) hex = hex + "0" + Integer.toString(b, 16);
        else hex = hex + Integer.toString(b, 16);


        return hex;

    }
}
