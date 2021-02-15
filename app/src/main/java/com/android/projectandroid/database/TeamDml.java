package com.android.projectandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.provider.BaseColumns;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.TeamAdapter;

import java.util.ArrayList;
import java.util.List;

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

    public void addLine(boolean favorite, String abrev, String city, String name, int logo){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TeamContract.TeamEntry.COLUMN_NAME_FAVORITE, favorite ? 1 : 0);
        values.put(TeamContract.TeamEntry.COLUMN_NAME_ABREV, abrev);
        values.put(TeamContract.TeamEntry.COLUMN_NAME_CITY, city);
        values.put(TeamContract.TeamEntry.COLUMN_NAME_NAME, name);
        values.put(TeamContract.TeamEntry.COLUMN_NAME_LOGO, logo);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TeamContract.TeamEntry.TABLE_NAME, null, values);
        //todo handle error if mauvaise insertion dans la bdd
    }

    public void readLine(){
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
}
