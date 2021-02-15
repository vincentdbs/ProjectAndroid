package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class FavoriteTeamActivity extends AppCompatActivity {
    private ListView list;
    private SwitchCompat switchFavorite;
    private ArrayList<Team> listOfTeam;
    private ArrayList<String> listOfFavoriteTeam;
    private TeamAdapter adapter;


    // todo ajouter un bouton remise à 0 de tous les favorirs -> clean la bdd
    // todo trouve un autre moyen car pas erreur en fonction de la version

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        //Find view by ID
        list = findViewById(R.id.lvFavoriteTeam);
        switchFavorite = findViewById(R.id.switchOnlyFavorite);

        //Get favorite team abbreviation from DB;
        listOfFavoriteTeam = getFavoriteFromDb();

        //Fill the list of team from constants => favorite if abbreviation in listOfFavoriteTeam
        listOfTeam = new ArrayList<>();
        MAP_LOGO_TEAM.forEach((str, team) -> {
            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), listOfFavoriteTeam.contains(team.getAbreviation())));
        });

        //Set the adapter
        adapter = new TeamAdapter(getApplicationContext(), listOfTeam);
        list.setAdapter(adapter);

        //Listener onClickSwitch
        onClickSwitch();
    }

    private void onClickSwitch(){
        switchFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                listOfTeam.clear();
                if(isChecked){
                    //        todo trouve un autre moyen car pas erreur en fonction de la version
                    MAP_LOGO_TEAM.forEach((str, team) -> {
                        if(team.isFavorites()){
                            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), team.isFavorites()));
                        }
                    });
                }else{
//        todo trouve un autre moyen car pas erreur en fonction de la version

                    MAP_LOGO_TEAM.forEach((str, team) -> {
                        listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), team.isFavorites()));
                    });
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ArrayList<String> getFavoriteFromDb(){
        TeamDml db = new TeamDml(getApplicationContext());
////        todo delete => here for test purpose
//        db.deleteAllTableContent();
//
//        db.addLine("SAS");
//        db.addLine("GSW");
//        db.addLine("LAC");


        return db.getAllFavTeamAbrev();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}