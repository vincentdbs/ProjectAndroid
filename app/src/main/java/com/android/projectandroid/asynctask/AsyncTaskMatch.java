package com.android.projectandroid.asynctask;

import android.util.Log;

import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.model.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class AsyncTaskMatch extends AsyncTaskStringJson {
    private MatchListAdapter adapter;

    public AsyncTaskMatch(MatchListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        adapter.clearMatches();
        Log.i(LOG_TAG, jsonObject.toString());
        try {
            JSONArray matches = jsonObject.getJSONArray("data");
            for (int i = 0; i < matches.length() ; i++) {
                adapter.add(new Match(
                        MAP_LOGO_TEAM.get(matches.getJSONObject(i).getJSONObject("home_team").getString("abbreviation")).getLogo(),
                        MAP_LOGO_TEAM.get(matches.getJSONObject(i).getJSONObject("visitor_team").getString("abbreviation")).getLogo(),
                        matches.getJSONObject(i).getJSONObject("home_team").getString("abbreviation"),
                        matches.getJSONObject(i).getJSONObject("visitor_team").getString("abbreviation"),
                        matches.getJSONObject(i).getString("home_team_score"),
                        matches.getJSONObject(i).getString("visitor_team_score"),
                        matches.getJSONObject(i).getString("status")
                ));
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
