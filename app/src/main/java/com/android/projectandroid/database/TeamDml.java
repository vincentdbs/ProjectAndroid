package com.android.projectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.TeamAdapter;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;
import java.util.List;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class TeamDml {
    private TeamDbHelper teamDbHelper;
    private SQLiteDatabase db;

    public TeamDml(Context context) {
        teamDbHelper = new TeamDbHelper(context);
        db = teamDbHelper.getWritableDatabase();
    }

    public void closeConnection(){
        teamDbHelper.close();
    }

    public void addLine(String abrev){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TeamContract.TeamEntry.COLUMN_NAME_ABREV, abrev);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TeamContract.TeamEntry.TABLE_NAME, null, values);
        Log.i(LOG_TAG, "Added " + newRowId);

        //todo handle error if mauvaise insertion dans la bdd
    }

    public Cursor readAllLine(){
        Cursor cursor = db.query(
                TeamContract.TeamEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TeamContract.TeamEntry._ID));
            itemIds.add(itemId);
        }
        Log.i(LOG_TAG, "Number of row" + itemIds.size());
        return cursor;
    }

    public ArrayList<String> getAllFavTeamAbrev(){
        Cursor cursor = db.query(
                TeamContract.TeamEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                TeamContract.TeamEntry.COLUMN_NAME_ABREV + " DESC"               // The sort order
        );

        ArrayList<String> favTeamAbrev = new ArrayList<>();
        while(cursor.moveToNext()) {
            String teamAbrev = cursor.getString(cursor.getColumnIndexOrThrow(TeamContract.TeamEntry.COLUMN_NAME_ABREV));
            favTeamAbrev.add(teamAbrev);
            Log.i(LOG_TAG, "Team abrev = " + teamAbrev);

        }
        Log.i(LOG_TAG, "Number of row" + favTeamAbrev.size());

        cursor.close();
        return favTeamAbrev;
    }



    public void deleteFilteredTableContent(String... filter){
        // Define 'where' part of query.
        String selection = TeamContract.TeamEntry.COLUMN_NAME_ABREV + " LIKE ?";

        // Issue SQL statement.
        int deletedRows = db.delete(TeamContract.TeamEntry.TABLE_NAME, selection, filter);
        Log.i(LOG_TAG, "Deleted " + deletedRows);
    }

    public void deleteAllTableContent(){
        // Issue SQL statement.
        int deletedRows = db.delete(TeamContract.TeamEntry.TABLE_NAME, null, null);
        Log.i(LOG_TAG, "Deleted " + deletedRows);
    }

}
