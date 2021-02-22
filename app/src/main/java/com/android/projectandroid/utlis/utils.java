package com.android.projectandroid.utlis;

import android.content.Context;
import android.widget.TextView;

import com.android.projectandroid.database.TeamDml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class utils {

    /**
     * Transform a InputStream into a String
     * @param is the input stream to read
     * @return the content of the stream in a String
     */
    public static String readStream(InputStream is) {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            return total.toString();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Capitalize the first letter of a string
     * @param str, the string to capitalize
     * @return the capitalized string
     */
    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Set the text of textviews in an array with the string passed in parameter
     * @param strToSet the text to set in the textviews
     * @param textViews an array of textviews
     */
    public static void clearTextViews(String strToSet, TextView... textViews){
        for (TextView tv: textViews) {
            tv.setText(strToSet);
        }
    }

    /**
     * Return today date in a string in format YYYY-MM-DD
     * @return today date
     */
    public static String getNowDate(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Return yesterday date in a string in format YYYY-MM-DD
     * @return yesterday date
     */
    public static String getYesterdayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get all favorite's team
     * @param context context of the application
     * @return ArrayList<String> list of favorite's team
     */
    public static ArrayList<String> getFavoriteFromDb(Context context){
        TeamDml db = new TeamDml(context);
        return db.getAllFavTeamAbrev();
    }


    /**
     * Format the favorite's team for the API call
     * @param context context context of the application
     * @return parameters of the API call
     */
    public static String getParamArrayOfApiTeamId(Context context){
        TeamDml db = new TeamDml(context);
        //Get the list of favorites team
        ArrayList<String> listOfFav = db.getAllFavTeamAbrev();
        StringBuilder param = new StringBuilder();
        for (String str: listOfFav) {
            param.append("&team_ids[]=").append(MAP_LOGO_TEAM.get(str).getApiId());
        }
        return (param.length() == 0) ? "&team_ids[]=0" : param.toString();
    }
}
