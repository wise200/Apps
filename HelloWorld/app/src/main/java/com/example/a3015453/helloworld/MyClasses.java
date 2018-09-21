package com.example.a3015453.helloworld;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MyClasses extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_classes);

        String[] days = {"AP Calculus BC", "AP Macroeconomics", "AP US Government", "AP Physics C: E & M", "AP English Lit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), R.layout.list_row_text, days);
        getListView().setAdapter(adapter);

        getListView().setOnTouchListener(new OnSwipeTouchListener(MyClasses.this) {

            @Override
            public void onSwipeLeft() {
                startActivity(new Intent(MyClasses.this, MainActivity.class));
            }
        });
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListAdapter adapter = l.getAdapter();
        String day = (String) adapter.getItem(position);
        //Launch Activity
    }
}
