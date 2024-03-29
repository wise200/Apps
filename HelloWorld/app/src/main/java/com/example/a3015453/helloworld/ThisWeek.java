package com.example.a3015453.helloworld;

import android.app.ListActivity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ThisWeek extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_week);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), R.layout.list_row_text, days);
        getListView().setAdapter(adapter);

        getListView().setOnTouchListener(new OnSwipeTouchListener(ThisWeek.this) {

            @Override
            public void onSwipeRight() {
                startActivity(new Intent(ThisWeek.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListAdapter adapter = l.getAdapter();
        String day = (String) adapter.getItem(position);
        //Launch Activity
    }
}
