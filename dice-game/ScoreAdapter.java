package com.example.wade_colton_assignment10;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ListItemHolder> {

    // List and Contest for constructor
    List<Player> playerList;
    Context myContext;

    // Class Constructor
    public ScoreAdapter(Context context, List<Player> pList)
    {
        myContext = context;
        playerList = pList;
    }


    @NonNull
    @Override
    public ScoreAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creates Layout for Recycle View
        LayoutInflater inflater = LayoutInflater.from(myContext);
        View view = inflater.inflate(R.layout.listitem, parent, false);

        return new ScoreAdapter.ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapter.ListItemHolder holder, int position) {

        // Sets RecycleView Data
        Player player = playerList.get(position);
        holder.PlayerName.setText(player.getName());
        holder.PlayerScore.setText(String.valueOf(player.getTotalScore()));
        holder.PlayerDoubles.setText(String.format("Doubles: %s", player.getTotalDoubles()));
        holder.PlayerTriples.setText(String.format("Triples: %s", player.getTotalTriples()));

        // Creates AlertDialog for holder
        holder.ListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(view.getRootView().getContext());

                View dialogView = LayoutInflater.from(view.getRootView().
                        getContext()).inflate(R.layout.player, null);

                TextView PlayerName = (TextView) dialogView.findViewById(R.id.playerNameTxtView);
                TextView PlayerRolls = (TextView) dialogView.findViewById(R.id.playerRollsTxtView);
                TextView PlayerScore = (TextView) dialogView.findViewById(R.id.playerScoreTxtView);
                TextView PlayerDoubles = (TextView) dialogView.findViewById(R.id.playerDoublesTxtView);
                TextView PlayerTriples = (TextView) dialogView.findViewById(R.id.playerTriplesTxtView);

                PlayerName.setText(player.getName());
                PlayerRolls.setText(String.format("Total Rolls: %s", player.getTotalRolls()));
                PlayerScore.setText(String.format("Total Score: %s", player.getTotalScore()));
                PlayerDoubles.setText(String.format("Total Doubles: %s", player.getTotalDoubles()));
                PlayerTriples.setText(String.format("Total Triples: %s", player.getTotalTriples()));


                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        // returns list size
        return playerList.size();
    }

    public static class ListItemHolder extends RecyclerView.ViewHolder{

        // Set's View's for Recycle View
        TextView PlayerName, PlayerScore, PlayerDoubles, PlayerTriples;
        ConstraintLayout ListItem;


        public ListItemHolder(View view) {
            super(view);
            // Set's Views
            ListItem = view.findViewById(R.id.list_item);
            PlayerName = view.findViewById(R.id.nameTxtView);
            PlayerScore = view.findViewById(R.id.scoreTxtView);
            PlayerDoubles = view.findViewById(R.id.txtViewDoubles);
            PlayerTriples = view.findViewById(R.id.txtViewTriples);

        }

    }
}


