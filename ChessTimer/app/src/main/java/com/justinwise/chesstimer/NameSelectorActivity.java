package com.justinwise.chesstimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.justinwise.chesstimer.customclasses.Player;

import java.util.ArrayList;

public class NameSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_selector);
        getSupportActionBar().setTitle("Enter Name");
        Bundle extras = getIntent().getExtras();
        final ArrayList<Player> players = extras.getParcelableArrayList("players");
        final int seconds = extras.getInt("seconds");
        final EditText nameField = (EditText) findViewById(R.id.nameField);
        final Button button = (Button) findViewById(R.id.select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NameSelectorActivity.this, ColorPickerActivity.class);
                intent.putParcelableArrayListExtra("players", players);
                intent.putExtra("seconds", seconds);
                String name = nameField.getText().toString();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        nameField.requestFocus();
    }
}
