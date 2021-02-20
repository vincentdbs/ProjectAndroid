package com.android.projectandroid.asynctask;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.projectandroid.utlis.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class AsyncTaskPlayerStats extends AsyncTaskStringJson {
    private TextView[] textViews;
    private String firstName, lastName, team, position, teamAbrev;
    private ImageView ivTeam;
    private Context context;

    public AsyncTaskPlayerStats(Context context, TextView[] textViews, ImageView ivTeam, String firstName, String lastName, String team, String teamAbrev, String position) {
        this.textViews = textViews;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.position = position;
        this.teamAbrev = teamAbrev;
        this.ivTeam = ivTeam;
        this.context = context;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {
            //Personal info of the player
            JSONArray data = jsonObject.getJSONArray("data");
            textViews[0].setText(utils.capitalize(firstName));
            textViews[1].setText(utils.capitalize(lastName));
            textViews[8].setText(team);
            textViews[9].setText(position);
            ivTeam.setImageResource(MAP_LOGO_TEAM.get(teamAbrev).getLogo());
            //Statistics of the player in the current season
            if(data.length() != 0){
                JSONObject playerStat = data.getJSONObject(0);
                textViews[2].setText(playerStat.getString("pts"));
                textViews[3].setText(playerStat.getString("ast"));
                textViews[4].setText(playerStat.getString("dreb"));
                textViews[5].setText(playerStat.getString("oreb"));
                textViews[6].setText(playerStat.getString("blk"));
                textViews[7].setText(playerStat.getString("stl"));
            }else{
                Toast.makeText(context, "This player did not played any match this season", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
