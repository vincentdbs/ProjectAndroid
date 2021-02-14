package com.android.projectandroid.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectandroid.R;
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

public class AsyncTaskPlayerId extends AsyncTask<String, Void, JSONObject> {
    private TextView[] textViews;
    private String firstName, lastName;
    private ImageView ivTeam;
    private Context context;

    public AsyncTaskPlayerId(Context context, String firstName, String lastName, ImageView ivTeam, TextView... textViews){
        this.textViews = textViews;
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.ivTeam = ivTeam;
        this.context = context;
    }


    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url;
        try {
            //Request the api to get all the player that has the same last name that the user want
            // Cant search by first and last time at the same time
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = utils.readStream(in);

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
        Log.i(LOG_TAG, jsonObject.toString());
        try {
            JSONArray players = jsonObject.getJSONArray("data");
            //Get the player index by searching by first and last name
            int jsonObjectIndex = getGoodPlayer(players);
            if(jsonObjectIndex != -1)  {
                JSONObject player = players.getJSONObject(jsonObjectIndex);
                String team = player.getJSONObject("team").getString("full_name");
                String teamAbrev = player.getJSONObject("team").getString("abbreviation");
                String position = player.getString("position");
                new AsyncTaskPlayerStats(context, textViews, ivTeam, firstName, lastName, team, teamAbrev, position).execute("https://www.balldontlie.io/api/v1/season_averages?player_ids[]=" + players.getJSONObject(jsonObjectIndex).getString("id"));
            }else{
                utils.clearTextViews("...", textViews);
                ivTeam.setImageResource(R.drawable.logo_nba);
                Toast.makeText(context, "No user has been found", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(jsonObject);
    }

    private int getGoodPlayer(JSONArray players){
        try {
            //Loop through all the found players to find the one with the first and last name wanted
            for (int i = 0; i < players.length() ; i++) {
                String fName = players.getJSONObject(i).getString("first_name").toLowerCase();
                String lName = players.getJSONObject(i).getString("last_name").toLowerCase();
                if((fName.equals(firstName)) && (lName.equals(lastName))){
                    //Return the index of the player in the JSONarray
                    return i;
                }
            }
        }catch (JSONException e) {
            Log.i(LOG_TAG,e.toString());
        }
        // Return -1 if the user is not in the array
        return -1;
    }
}
