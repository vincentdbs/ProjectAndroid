package com.android.projectandroid.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.utlis.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class AsyncTaskMatch extends AsyncTask<String, Void, JSONObject> {
    private MatchListAdapter adapter;

    public AsyncTaskMatch(MatchListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
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

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url;
        try {
            //Replace http by https
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = utils.readStream(in);

                //Get the authenticated value
                JSONObject result = new JSONObject(s);
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.i(LOG_TAG, e.toString());
        } catch (IOException e) {
            Log.i(LOG_TAG, e.toString());
        }
        return null;
    }
}
