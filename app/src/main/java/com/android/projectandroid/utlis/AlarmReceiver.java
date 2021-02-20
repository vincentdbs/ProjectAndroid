package com.android.projectandroid.utlis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.projectandroid.asynctask.AsyncTaskMatch;
import com.android.projectandroid.asynctask.AsyncTaskMatchNotification;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;
import static com.android.projectandroid.utlis.utils.getFavoriteFromDb;

public class AlarmReceiver extends BroadcastReceiver {
    /*
    * The action to do when we receive the trigger
    * */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "broadcast receiver");

        //todo today - 1
        String paramDate = utils.getYesterdayDate();
        String param = "start_date=" + paramDate+ "&end_date=" + paramDate + utils.getParamArrayOfApiTeamId(context);

        new AsyncTaskMatchNotification(context).execute("https://www.balldontlie.io/api/v1/games?" + param);
    }
}
