package com.android.projectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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

    /**
     * Add an Team abreviation to the favorite team table in the database
     * @param abrev, the string to add
     */
    public void addLine(String abrev){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TeamContract.TeamEntry.COLUMN_NAME_ABREV, abrev);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TeamContract.TeamEntry.TABLE_NAME, null, values);
        Log.i(LOG_TAG, "Added " + newRowId);

        //todo handle error if bad insertion dans la bdd
    }

    /**
     * Read all the line of the Team table
     * @return the cursor that contain all the IDs of the Team table
     */
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

        //Add each item's ID to the list
        ArrayList<Long> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(TeamContract.TeamEntry._ID));
            itemIds.add(itemId);
        }
        Log.i(LOG_TAG, "Number of row" + itemIds.size());
        return cursor;
    }

    /**
     * Get the list of team abreviation from the databse
     * @return ArrayList<String> that contains all the favorite's team abreviation
     */
    public ArrayList<String> getAllFavTeamAbrev(){
        //Execute the SQL query
        Cursor cursor = db.query(
                TeamContract.TeamEntry.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                TeamContract.TeamEntry.COLUMN_NAME_ABREV + " DESC"               // The sort order
        );

        //Store each value from the database in the list
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


    /**
     * Delete the lines equals the filter
     * @param filter the filter in the "like" part of the SQL query
     */
    public void deleteFilteredTableContent(String... filter){
        // Define 'like' part of query.
        String selection = TeamContract.TeamEntry.COLUMN_NAME_ABREV + " LIKE ?";

        // Issue SQL statement.
        int deletedRows = db.delete(TeamContract.TeamEntry.TABLE_NAME, selection, filter);
        Log.i(LOG_TAG, "Deleted " + deletedRows);
    }

    /**
     * Delete all the line of the Team table
     */
    public void deleteAllTableContent(){
        // Issue SQL statement.
        int deletedRows = db.delete(TeamContract.TeamEntry.TABLE_NAME, null, null);
        Log.i(LOG_TAG, "Deleted " + deletedRows);
    }

}
