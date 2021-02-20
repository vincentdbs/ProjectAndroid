package com.android.projectandroid.utlis;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.android.projectandroid.R;
import com.android.projectandroid.activity.MainActivity;
import com.android.projectandroid.model.Match;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class NotificationHelper {
    private ArrayList<Match> listOfMatch;
    private Context mContext;
    private static final String NOTIFICATION_CHANNEL_ID = "10001";

    public NotificationHelper(Context context, ArrayList<Match> list) {
        mContext = context;
        listOfMatch = list;
    }

    public void createNotification() {
        Log.i(LOG_TAG, "create Notification");

        Intent intent = new Intent(mContext , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 , intent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_sports_basketball_32);

        StringBuilder contentNotification = new StringBuilder();
        for (Match match: listOfMatch) {
            contentNotification.append(match.getNameTeamDom()).append(" : ").append(match.getScoreTeamDom()).append(" - ").append(match.getScoreTeamExt()).append(" : ").append(match.getNameTeamExt()).append(" | ");
        }

        Log.i(LOG_TAG, contentNotification.toString());

        mBuilder.setContentTitle("Recap of the night")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(contentNotification.toString()))
                .setContentText(contentNotification.toString())
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.setDescription("description");

            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }
}