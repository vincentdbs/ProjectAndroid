package com.android.projectandroid.asynctask;

import android.content.Context;
import android.util.Log;

import com.android.projectandroid.model.Match;
import com.android.projectandroid.utlis.NotificationHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class AsyncTaskMatchNotification extends AsyncTaskStringJson {
    private Context context;

    public AsyncTaskMatchNotification(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            JSONArray matches = jsonObject.getJSONArray("data");
            ArrayList<Match>  matchArrayList = new ArrayList<>();
            for (int i = 0; i < matches.length() ; i++) {
                matchArrayList.add(new Match(
                        MAP_LOGO_TEAM.get(matches.getJSONObject(i).getJSONObject("home_team").getString("abbreviation")).getLogo(),
                        MAP_LOGO_TEAM.get(matches.getJSONObject(i).getJSONObject("visitor_team").getString("abbreviation")).getLogo(),
                        matches.getJSONObject(i).getJSONObject("home_team").getString("abbreviation"),
                        matches.getJSONObject(i).getJSONObject("visitor_team").getString("abbreviation"),
                        matches.getJSONObject(i).getString("home_team_score"),
                        matches.getJSONObject(i).getString("visitor_team_score"),
                        matches.getJSONObject(i).getString("status")
                ));
            }

            Log.i(LOG_TAG, "Asynctask --------------");

            NotificationHelper notificationHelper = new NotificationHelper(context, matchArrayList);
            notificationHelper.createNotification();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
