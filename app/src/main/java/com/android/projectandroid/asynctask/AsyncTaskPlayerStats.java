package com.android.projectandroid.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class AsyncTaskPlayerStats extends AsyncTask<String, Void, JSONObject> {
    private TextView[] textViews;
    private String firstName, lastName, team;

    public AsyncTaskPlayerStats(TextView[] textViews, String firstName, String lastName, String team) {
        this.textViews = textViews;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url;
        try {
            //Request the api to get the player stats
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = utils.readStream(in);

                Log.i(LOG_TAG, s);

                //Get the authenticated value
                return new JSONObject(s);
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

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            JSONArray data = jsonObject.getJSONArray("data");
            JSONObject playerStat = data.getJSONObject(0);

            textViews[0].setText(utils.capitalize(firstName));
            textViews[1].setText(utils.capitalize(lastName));
            textViews[2].setText(playerStat.getString("pts"));
            textViews[3].setText(playerStat.getString("ast"));
            textViews[4].setText(playerStat.getString("dreb"));
            textViews[5].setText(playerStat.getString("oreb"));
            textViews[6].setText(playerStat.getString("blk"));
            textViews[7].setText(playerStat.getString("stl"));
            textViews[8].setText(team);
            textViews[9].setText("9");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
