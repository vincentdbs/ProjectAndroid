package com.android.projectandroid.activity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projectandroid.R;
import com.android.projectandroid.asynctask.AsyncTaskPlayerId;

public class PlayerStatsActivity extends AppCompatActivity {
    //todo le poste n'est pas récuperer et changé
    private TextView tvFirstName, tvLastName, tvTeam, tvNumber, tvPoints, tvAssists, tvReboundsO, tvReboundsD, tvSteals, tvBlocks, tvPosition;

    private SearchView svSearchPlayer;
    private String playerToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        initFields();
        TextView[] textViews = {tvFirstName, tvLastName, tvPoints, tvAssists, tvReboundsD, tvReboundsO, tvBlocks, tvSteals,tvTeam, tvNumber, tvPosition};

        new AsyncTaskPlayerId("Giannis", "Antetokounmpo", textViews).execute("https://www.balldontlie.io/api/v1/players?search=antetokounmpo");

    }

    private void initFields(){
        svSearchPlayer = findViewById(R.id.svSearchPlayer);
        tvFirstName = findViewById(R.id.tv_playerFirstName);
        tvLastName = findViewById(R.id.tv_playerName);
        tvPoints = findViewById(R.id.tv_points);
        tvAssists = findViewById(R.id.tv_assists);
        tvReboundsO = findViewById(R.id.tv_reboundO);
        tvReboundsD = findViewById(R.id.tv_reboundD);
        tvBlocks = findViewById(R.id.tv_blocks);
        tvSteals = findViewById(R.id.tv_steals);
        tvTeam = findViewById(R.id.tv_playerTeam);
        tvNumber = findViewById(R.id.tv_playerNumber);
        tvPosition = findViewById(R.id.tv_playerPost);
    }

}