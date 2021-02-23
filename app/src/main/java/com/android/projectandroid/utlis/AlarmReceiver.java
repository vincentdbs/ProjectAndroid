package com.android.projectandroid.utlis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.projectandroid.asynctask.AsyncTaskMatchNotification;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class AlarmReceiver extends BroadcastReceiver {
    /**
     * Call asyntask to get the yesterday's list of match
     * @param context the app context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "broadcast receiver");

        String paramDate = utils.getYesterdayDate();
        String param = "start_date=" + paramDate+ "&end_date=" + paramDate + utils.getParamArrayOfApiTeamId(context);

        new AsyncTaskMatchNotification(context).execute("https://www.balldontlie.io/api/v1/games?" + param);
    }
}
