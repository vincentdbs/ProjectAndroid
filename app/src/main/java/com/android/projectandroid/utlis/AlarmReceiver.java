package com.android.projectandroid.utlis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class AlarmReceiver extends BroadcastReceiver {
    /*
    * The action to do when we receive the trigger
    * */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "broadcast receiver");
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.createNotification();
    }
}
