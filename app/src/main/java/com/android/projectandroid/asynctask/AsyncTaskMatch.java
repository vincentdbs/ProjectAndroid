package com.android.projectandroid.asynctask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.model.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class AsyncTaskMatch extends AsyncTaskStringJson {
    private MatchListAdapter adapter;
    private Context context;


    public AsyncTaskMatch(MatchListAdapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }

    /**
     * Fill the adapter's list with the result of the API call
     * @param jsonObject, the JSONObject that contains all the wanted matches
     */
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        //Clear the previous list in the adapter
        adapter.clearMatches();
        try {
            if(jsonObject != null){
                Log.i(LOG_TAG, jsonObject.toString());
                JSONArray matches = jsonObject.getJSONArray("data");
                //Add each match to the list in the adapter
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
            }

            //Notify the adapter that its data set has changed
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_LONG).show();
        }
    }
}
