package com.justinwise.studyland.Custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by jwise200 on 7/23/2017.
 */

public class MenuAdapter extends BaseAdapter {

    private ArrayList<String> displayText;
    private Context context;

    public MenuAdapter(ArrayList<String> categories, Context newContext) {
        displayText = categories;
        context = newContext;
    }

    @Override
    public int getCount() {
        return displayText.size();
    }

    @Override
    public Object getItem(int i) {
        return displayText.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
            view = new StudySetView(context, displayText.get(i), 250, false);
        else
            ((StudySetView) view).set(displayText.get(i), 250, false);
        return view;
    }
}
