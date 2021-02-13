package com.android.projectandroid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projectandroid.R;
import com.android.projectandroid.asynctask.AsyncTaskMatch;
import com.android.projectandroid.asynctask.AsyncTaskPlayerStats;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class PlayerStatsActivity extends AppCompatActivity {
    private TextView tvFirstName, tvLastName, tvTeam, tvNumber, tvPoints, tvAssists, tvReboundsO, tvReboundsD, tvSteals, tvBlocks;
    private SearchView svSearchPlayer;
    private String playerToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        new AsyncTaskPlayerStats("Lebron", "James").execute("https://www.balldontlie.io/api/v1/players?search=lebron");

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
    }

}