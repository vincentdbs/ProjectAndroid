package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.RecyclerTeamAdapter;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;
import static com.android.projectandroid.utlis.utils.getFavoriteFromDb;

public class FavoriteTeamActivity extends AppCompatActivity {
    private SwitchCompat switchFavorite;
    private ArrayList<Team> listOfTeam;
    private ArrayList<String> listOfFavoriteTeam;
    private RecyclerTeamAdapter adapter;
    private Button btnResetFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        //Find view by ID
        switchFavorite = findViewById(R.id.switchOnlyFavorite);
        btnResetFav = findViewById(R.id.btnResetFav);
        ImageView ivBackArrow = findViewById(R.id.svgArrowBackTeam);

        ivBackArrow.setOnClickListener(v -> finish());

        //Get favorite team abbreviation from DB;
        listOfFavoriteTeam = getFavoriteFromDb(getApplicationContext());

        //Fill the list of team from constants => favorite if abbreviation in listOfFavoriteTeam
        listOfTeam = new ArrayList<>();
        for (Team team : MAP_LOGO_TEAM.values()) {
            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), listOfFavoriteTeam.contains(team.getAbreviation())));
        }

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvFavoriteTeam);

        // Create adapter passing in the sample user data
        adapter = new RecyclerTeamAdapter(listOfTeam, getApplicationContext());
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        //Listener
        onClickSwitch();
        btnResetFavOnClickListener();
    }

    private void onClickSwitch(){
        switchFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
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
        });
    }

    public void btnResetFavOnClickListener(){
        btnResetFav.setOnClickListener(view -> {
            TeamDml db = new TeamDml(getApplicationContext());
            db.deleteAllTableContent();
            listOfTeam.clear();
            if(!switchFavorite.isChecked()){
                for (Team team : MAP_LOGO_TEAM.values()) {
                    listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), false));
                }
            }

            adapter.notifyDataSetChanged();
        });
    }
}