package com.android.projectandroid.database;

import android.provider.BaseColumns;

import com.android.projectandroid.model.Team;

public class TeamContract {
    private TeamContract() { }

    /* Inner class that defines the table contents */
    public static class TeamEntry implements BaseColumns {
        public static final String TABLE_NAME = "team";
        public static final String COLUMN_NAME_ABREV = "abrev";
        public static final String COLUMN_NAME_FAVORITE = "favorite";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_LOGO = "logo";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TeamEntry.TABLE_NAME + " (" +
                        TeamEntry._ID + " INTEGER PRIMARY KEY," +
                        TeamEntry.COLUMN_NAME_ABREV + " TEXT," +
                        TeamEntry.COLUMN_NAME_FAVORITE + " INTEGER," +
                        TeamEntry.COLUMN_NAME_NAME + " TEXT," +
                        TeamEntry.COLUMN_NAME_CITY + " TEXT," +
                        TeamEntry.COLUMN_NAME_LOGO + " INTEGER)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TeamEntry.TABLE_NAME;
    }


}
