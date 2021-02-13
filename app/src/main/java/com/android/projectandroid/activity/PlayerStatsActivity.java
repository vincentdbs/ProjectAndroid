package com.android.projectandroid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projectandroid.R;
import com.android.projectandroid.asynctask.AsyncTaskPlayerId;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class PlayerStatsActivity extends AppCompatActivity {
    //todo remplacer le numéro du joueur par le logo de l'équipe
    //todo clear the fields if no user found
    private TextView tvFirstName, tvLastName, tvTeam, tvPoints, tvAssists, tvReboundsO, tvReboundsD, tvSteals, tvBlocks, tvPosition;

    private SearchView svSearchPlayer;
    private String playerToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        initFields();
        TextView[] textViews = {tvFirstName, tvLastName, tvPoints, tvAssists, tvReboundsD, tvReboundsO, tvBlocks, tvSteals,tvTeam, tvPosition};


        svSearchPlayer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String[] splited = s.split(" ");
                if (splited.length == 1){
                    Toast.makeText(getApplicationContext(), "You should entrer a last name",
                            Toast.LENGTH_LONG).show();
                }else{
                    svSearchPlayer.setIconified(true);
                    Log.i(LOG_TAG, splited[0] + " " + splited[1]);
                    new AsyncTaskPlayerId(splited[0].toLowerCase(), splited[1].toLowerCase(), textViews)
                            .execute("https://www.balldontlie.io/api/v1/players?per_page=100&search=" + splited[1].toLowerCase());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

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
        tvPosition = findViewById(R.id.tv_playerPost);
    }

}