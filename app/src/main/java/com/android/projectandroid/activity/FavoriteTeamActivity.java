package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

public class FavoriteTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        ArrayList<Team> listOfTeam = new ArrayList<>();
        listOfTeam.add(new Team(R.drawable.logo_76ers, "76ers", "76ers", "Philadelphia", true));
        listOfTeam.add(new Team(R.drawable.logo_bucks, "76ers", "76ers", "Philadelphia", true));
        listOfTeam.add(new Team(R.drawable.logo_cavaliers, "76ers", "76ers", "Philadelphia", true));
        listOfTeam.add(new Team(R.drawable.logo_clippers, "76ers", "76ers", "Philadelphia", true));

        TeamAdapter adapter = new TeamAdapter(getApplicationContext(), listOfTeam);

        ListView list = (ListView) findViewById(R.id.lvFavoriteTeam);

        list.setAdapter(adapter);
    }
}