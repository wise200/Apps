package com.justinwise.chesstimer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.justinwise.chesstimer.customclasses.Player;
import com.justinwise.chesstimer.customclasses.PlayerListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Player> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("players"))
            playerList = getIntent().getExtras().getParcelableArrayList("players");
        else {
            playerList = new ArrayList<Player>();
            emptyLaunch();
        }

        final ListView playerListView = (ListView) findViewById(R.id.playerList);
        playerListView.setAdapter(new PlayerListAdapter(getApplicationContext(), playerList));

        View footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        playerListView.addFooterView(footer);

        TextView add = (TextView) footer.findViewById(R.id.add);
        TextView start = (TextView) footer.findViewById(R.id.start);

        final Context context = this;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerList.size() < 2) {
                    Toast.makeText(context, "Not enough players", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                    intent.putParcelableArrayListExtra("players", playerList);
                    startActivity(intent);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerList.size() < 1)
                    emptyLaunch();
                else {
                    Intent intent = new Intent(MainActivity.this, NameSelectorActivity.class);
                    intent.putParcelableArrayListExtra("players", playerList);
                    intent.putExtra("seconds", playerList.get(playerList.size() - 1).getSeconds());
                    startActivity(intent);
                }
            }
        });
    }

    private void emptyLaunch() {
        Intent intent = new Intent(MainActivity.this, NameSelectorActivity.class);
        intent.putParcelableArrayListExtra("players", playerList);
        intent.putExtra("seconds", 300);
        startActivity(intent);
    }
}
