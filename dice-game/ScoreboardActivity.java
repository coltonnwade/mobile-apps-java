package com.example.wade_colton_assignment10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ScoreboardActivity extends AppCompatActivity{

    // Views
    public TextView TotalRoll;
    public TextView ThanksMessage;
    public RecyclerView Scoreboard;
    final List<Player> playerList = new ArrayList<>();

    AnimationManager am = new AnimationManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoreboard);


        setUIElements(); // Set's UI Elements
        am.loadAnimations(this);

        // Get's Intent
        Bundle bundle = getIntent().getExtras(); // Creates Bundle to get Extra's
        // Pulls everything out of the intent
        Gson gson = new Gson();
        String jsonDice = bundle.getString("Dice");
        Dice dice = gson.fromJson(jsonDice, Dice.class);
        String username = bundle.getString("username");

        TotalRoll.setText(String.format("%s Total Dice Rolls: ", dice.getRolls())); // Display's total rolls
        ThanksMessage.setText(String.format("Thanks For Playing %s", username)); // Displays username in thanks message

        GetPlayersFromSharedPreferences(); // Gets Players
        CreateRecycleView(); // Creates Scoreboard
        am.TopBottom(Scoreboard, 1000);

        // Returns to Main Activity
        Button Return = findViewById(R.id.btnReturn);
        Return.setOnClickListener(view -> {
         //    Sends Intent back to MainActivity to preserve player's data
            Intent returnBtn = new Intent(getBaseContext(), MainActivity.class);
            String Dice = gson.toJson(dice);
            returnBtn.putExtra("Dice", Dice);
            returnBtn.putExtra("player", username);
            startActivity(returnBtn);
        });
    }

    // Set's UI Elements
    public void setUIElements()
    {
        TotalRoll = findViewById(R.id.txtViewDiceRolls);
        ThanksMessage = findViewById(R.id.txtViewThanks);
    }

    public void GetPlayersFromSharedPreferences()
    {

        SharedPreferences Stats = getSharedPreferences("DiceGame", MODE_PRIVATE); // Gets SharedPreferences
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor myEdit = Stats.edit();
        Map<String, ?> allEntries = (Map<String, ?>) Stats.getAll(); // Creates Map of all Shared Preferences

        // Loops through map and retrieves player objects from shared preferences
        // Creates player object from shared preferences, and then appends it to playerList
        for(Map.Entry<String, ?> entry : allEntries.entrySet()) {

            Gson gson = new Gson();
            String json = Stats.getString(entry.getKey(), entry.getValue().toString());
            Player player = gson.fromJson(json, Player.class);
            playerList.add(player);
            //Log.d("DEBUG", String.valueOf(player));
        }

        // Sort's List in ascending order
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getTotalScore(), p1.getTotalScore());
            }
        });

        // Debug Loop through
        for(Player s: playerList)
        {
            Log.d("DEBUG Player Objects", s.toString());
        }
    }

    public void CreateRecycleView()
    {
        Scoreboard = findViewById(R.id.scoreboardRecycleView);
        ScoreAdapter diceAdapter = new ScoreAdapter(this, playerList);
        Scoreboard.setAdapter(diceAdapter);
        Scoreboard.setLayoutManager(new LinearLayoutManager(this));
        Scoreboard.setItemAnimator(new DefaultItemAnimator());
    }
}
