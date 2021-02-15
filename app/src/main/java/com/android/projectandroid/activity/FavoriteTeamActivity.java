package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class FavoriteTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);
        ArrayList<Team> listOfTeam = new ArrayList<>();

//        todo trouve un autre moyen car pas erreur en fonction de la version
        // todo remplacer par une bdd locale
        MAP_LOGO_TEAM.forEach((str, team) -> {
            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), team.isFavorites()));
        });


        TeamAdapter adapter = new TeamAdapter(getApplicationContext(), listOfTeam);

        ListView list = (ListView) findViewById(R.id.lvFavoriteTeam);

        list.setAdapter(adapter);
    }
}