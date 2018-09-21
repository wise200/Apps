package Custom;


import android.view.View;

import java.util.ArrayList;

/**
 * Created by jwise200 on 1/31/2017.
 */

public class ViewList extends ArrayList<View> {
    public void deleteAll()
    {
        for (int i = size() - 1; i >= 0; i--)
        {
            get(i).setVisibility(View.INVISIBLE);
            remove(i);
        }
    }
}
