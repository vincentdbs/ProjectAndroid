package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.database.TeamContract;
import com.android.projectandroid.database.TeamDbHelper;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;
import java.util.List;

import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class FavoriteTeamActivity extends AppCompatActivity {
    private ListView list;
    private SwitchCompat switchFavorite;
    private ArrayList<Team> listOfTeam;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        list = (ListView) findViewById(R.id.lvFavoriteTeam);
        switchFavorite = findViewById(R.id.switchOnlyFavorite);

        // todo ajouter un bouton remise à 0 de tous les favorirs -> clean la bdd

//        todo trouve un autre moyen car pas erreur en fonction de la version
        // todo remplacer par une bdd locale
        listOfTeam = new ArrayList<>();
        MAP_LOGO_TEAM.forEach((str, team) -> {
            listOfTeam.add(new Team(team.getLogo(), team.getName(), team.getAbreviation(), team.getCity(), team.isFavorites()));
        });


        adapter = new TeamAdapter(getApplicationContext(), listOfTeam);
        list.setAdapter(adapter);

        onClickSwitch();

        database();
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

    public void database(){
        TeamDml db = new TeamDml(getApplicationContext());
        db.readLine();


        db.deleteTableContent();

        db.readLine();


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}