package com.android.projectandroid.utlis;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

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
}
