package com.android.projectandroid.utlis;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.projectandroid.database.TeamDml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class utils {
    //ReadStream is a function that the developer need to implement
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

    public static String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void clearTextViews(String strToSet, TextView... textViews){
        for (TextView tv: textViews) {
            tv.setText(strToSet);
        }
    }

    public static String getNowDate(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String getYesterdayDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
    }

    public static ArrayList<String> getFavoriteFromDb(Context context){
        TeamDml db = new TeamDml(context);
////        todo delete => here for test purpose
//        db.deleteAllTableContent();
//
//        db.addLine("SAS");
//        db.addLine("GSW");
//        db.addLine("LAC");

        return db.getAllFavTeamAbrev();
    }


    public static String getParamArrayOfApiTeamId(Context context){
        TeamDml db = new TeamDml(context);
        ArrayList<String> listOfFav = db.getAllFavTeamAbrev();
        StringBuilder param = new StringBuilder();
        for (String str: listOfFav) {
            param.append("&team_ids[]=").append(MAP_LOGO_TEAM.get(str).getApiId());
        }
        return (param.length() == 0) ? "&team_ids[]=0" : param.toString();
    }
}
