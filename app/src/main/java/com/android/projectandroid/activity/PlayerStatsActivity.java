package com.android.projectandroid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projectandroid.R;
import com.android.projectandroid.asynctask.AsyncTaskPlayerId;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class PlayerStatsActivity extends AppCompatActivity {
    private TextView[] textViews;
    private ImageView ivTeam;
    private SearchView svSearchPlayer;
    private String playerToSearch;
    private ImageView svgArrowBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        initFields();
        addQueryListener();
        addOnClickListenerArrow();
    }

    private void initFields(){
        //Logo team
        ivTeam = findViewById(R.id.logoTeam);
        //Search view
        svSearchPlayer = findViewById(R.id.svSearchPlayer);
        //Svg
        svgArrowBack = findViewById(R.id.svgArrowBack);
        //Text view
        TextView tvFirstName = findViewById(R.id.tv_playerFirstName);
        TextView tvLastName = findViewById(R.id.tv_playerName);
        TextView tvPoints = findViewById(R.id.tv_points);
        TextView tvAssists = findViewById(R.id.tv_assists);
        TextView tvReboundsO = findViewById(R.id.tv_reboundO);
        TextView tvReboundsD = findViewById(R.id.tv_reboundD);
        TextView tvBlocks = findViewById(R.id.tv_blocks);
        TextView tvSteals = findViewById(R.id.tv_steals);
        TextView tvTeam = findViewById(R.id.tv_playerTeam);
        TextView tvPosition = findViewById(R.id.tv_playerPost);
        textViews = new TextView[]{tvFirstName, tvLastName, tvPoints, tvAssists, tvReboundsD, tvReboundsO, tvBlocks, tvSteals, tvTeam, tvPosition};
    }

    private void addQueryListener(){
        svSearchPlayer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String[] splited = s.split(" ");
                if (splited.length == 1){
                    Toast.makeText(getApplicationContext(), "You should enter a last name",
                            Toast.LENGTH_LONG).show();
                }else{
                    svSearchPlayer.setIconified(true);
                    Log.i(LOG_TAG, splited[0] + " " + splited[1]);
                    new AsyncTaskPlayerId(getApplicationContext(), splited[0].toLowerCase(), splited[1].toLowerCase(), ivTeam, textViews)
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

    private void addOnClickListenerArrow(){
        svgArrowBack.setOnClickListener(view -> finish());
    }
}