package com.justinwise.studyland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.justinwise.studyland.Custom.MenuAdapter;
import com.justinwise.studyland.Custom.StudySetView;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private ListView list;
    private boolean[] selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        list = (ListView) findViewById(R.id.list);
        ArrayList<String> items = new ArrayList<>();
        items.add("Science");
        items.add("Social Science");
        items.add("Literature");
        items.add("Mathematics");
        items.add("Art");
        items.add("Music");
        items.add("Economics");
        selected = new boolean[items.size()];
        list.addFooterView(new StudySetView(this, "Go", 250, true));
        list.setAdapter(new MenuAdapter(items, this));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                StudySetView current = (StudySetView) view;
                if (current.isButton()) {
                    Intent intent = new Intent(MainMenu.this, AstroBlitz.class);
                    intent.putExtra("sets", selected);
                    startActivity(intent);
                } else {
                    current.toggle();
                    selected[i] = !selected[i];
                }
            }
        });
    }
}
