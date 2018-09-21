package com.justinwise.chesstimer.customclasses;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.justinwise.chesstimer.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jwise200 on 6/28/2017.
 */

public class PlayerListAdapter extends BaseAdapter {

    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Context context;

    public PlayerListAdapter(Context newContext, ArrayList<Player> newList) {
        context = newContext;
        playerList = newList;
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int i) {
        return playerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView = view;
        if (newView == null) {
            LayoutInflater inflator = LayoutInflater.from(context);
            newView = inflator.inflate(R.layout.player_list_row, null);
        }
        View colorHint = newView.findViewById(R.id.colorHint);
        TextView name = (TextView) newView.findViewById(R.id.name);
        final TextView time = (TextView) newView.findViewById(R.id.time);
        View upArrow = newView.findViewById(R.id.upArrow);
        View downArrow = newView.findViewById(R.id.downArrow);
        View x = newView.findViewById(R.id.x);

        final Player player = (Player) getItem(i);
        final int pos = i;

        name.setText(player.getName());
        time.setText(player.getTime());

        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getSeconds() == 15)
                    player.changeSeconds(15);
                else if (player.getSeconds() == 30)
                    player.changeSeconds(30);
                else
                    player.changeSeconds(60);

                time.setText(player.getTime());
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.getSeconds() == 60)
                    player.changeSeconds(-30);
                else if (player.getSeconds() == 30)
                    player.changeSeconds(-15);
                else if (player.getSeconds() > 60)
                    player.changeSeconds(-60);
                time.setText(player.getTime());
            }
        });

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerList.remove(pos);
                notifyDataSetChanged();
            }
        });

        GradientDrawable bg = (GradientDrawable) colorHint.getBackground();
        bg.setColor(Color.parseColor(player.getColor().toString()));

        return newView;
    }

}