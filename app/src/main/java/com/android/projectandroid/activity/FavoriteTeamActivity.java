package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Team;
import com.android.projectandroid.utlis.constants;
import com.android.projectandroid.utlis.utils;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;
import static com.android.projectandroid.utlis.utils.getFavoriteFromDb;

public class FavoriteTeamActivity extends AppCompatActivity {
    private ListView list;
    private SwitchCompat switchFavorite;
    private ArrayList<Team> listOfTeam;
    private ArrayList<String> listOfFavoriteTeam;
    private TeamAdapter adapter;
    private Button btnResetFav;
    private ImageView ivBackArrow;

    //todo recycler view instead of list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        //Find view by ID
        list = findViewById(R.id.lvFavoriteTeam);
        switchFavorite = findViewById(R.id.switchOnlyFavorite);
        btnResetFav = findViewById(R.id.btnResetFav);
        ivBackArrow = findViewById(R.id.svgArrowBackTeam);

        ivBackArrow.setOnClickListener(v -> finish());

        //Get favorite team abbreviation from DB;
        listOfFavoriteTeam = getFavoriteFromDb(getApplicationContext());

        //Fill the list of team from constants => favorite if abbreviation in listOfFavoriteTeam
        listOfTeam = new ArrayList<>();
        for (Team team : MAP_LOGO_TEAM.values()) {
            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), listOfFavoriteTeam.contains(team.getAbreviation())));
        }

        //Set the adapter
        adapter = new TeamAdapter(getApplicationContext(), listOfTeam);
        list.setAdapter(adapter);

        //Listener
        onClickSwitch();
        btnResetFavOnClickListener();
    }

    private void onClickSwitch(){
        switchFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Get the new list of favorite team
                listOfFavoriteTeam = getFavoriteFromDb(getApplicationContext());
                //Clear the old list of team
                listOfTeam.clear();
                if(isChecked){
                    for (Team team : MAP_LOGO_TEAM.values()) {
                        //Only add the favorite team to the list
                        if(listOfFavoriteTeam.contains(team.getAbreviation())){
                            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), true));
                        }
                    }

                }else{
                    for (Team team : MAP_LOGO_TEAM.values()) {
                        listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), listOfFavoriteTeam.contains(team.getAbreviation())));
                    }
                }
                //Notify the adapter that the data set changed
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void btnResetFavOnClickListener(){
        btnResetFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamDml db = new TeamDml(getApplicationContext());
                db.deleteAllTableContent();
                listOfTeam.clear();
                if(!switchFavorite.isChecked()){
                    for (Team team : MAP_LOGO_TEAM.values()) {
                        listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), false));
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}