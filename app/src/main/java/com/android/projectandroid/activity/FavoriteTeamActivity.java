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
import com.android.projectandroid.model.Team;

import java.util.ArrayList;
import java.util.List;

import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class FavoriteTeamActivity extends AppCompatActivity {
    private ListView list;
    private SwitchCompat switchFavorite;
    private ArrayList<Team> listOfTeam;
    private TeamAdapter adapter;
    private TeamDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_team);

        list = (ListView) findViewById(R.id.lvFavoriteTeam);
        switchFavorite = findViewById(R.id.switchOnlyFavorite);

        dbHelper = new TeamDbHelper(getApplicationContext());

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


        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TeamContract.TeamEntry.COLUMN_NAME_FAVORITE, 1);
        values.put(TeamContract.TeamEntry.COLUMN_NAME_ABREV, "SAS");
        values.put(TeamContract.TeamEntry.COLUMN_NAME_CITY, "San Antonio");
        values.put(TeamContract.TeamEntry.COLUMN_NAME_NAME, "Spurs");
        values.put(TeamContract.TeamEntry.COLUMN_NAME_LOGO, R.drawable.logo_spurs);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TeamContract.TeamEntry.TABLE_NAME, null, values);



// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                TeamContract.TeamEntry.COLUMN_NAME_FAVORITE,
                TeamContract.TeamEntry.COLUMN_NAME_ABREV,
                TeamContract.TeamEntry.COLUMN_NAME_CITY,
                TeamContract.TeamEntry.COLUMN_NAME_NAME,
                TeamContract.TeamEntry.COLUMN_NAME_LOGO
        };

// Filter results WHERE "title" = 'My Title'
        String selection = TeamContract.TeamEntry.COLUMN_NAME_ABREV + " = ?";
        String[] selectionArgs = { "SAS" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                TeamContract.TeamEntry.COLUMN_NAME_ABREV + " DESC";

        Cursor cursor = db.query(
                TeamContract.TeamEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(TeamContract.TeamEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();

    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

}