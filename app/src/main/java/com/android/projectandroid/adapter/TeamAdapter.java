package com.android.projectandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.model.Team;

import java.util.ArrayList;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class TeamAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Team> teams;

    public TeamAdapter(Context context, ArrayList<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    public TeamAdapter(Context context) {
        this.context = context;
        this.teams = new ArrayList<Team>();
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int i) {
        return teams.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_team, viewGroup, false);
        }

        ImageView logo = convertView.findViewById(R.id.ivLogoTeamFavorite);
        ImageView star = convertView.findViewById(R.id.ivStar);
        TextView tvNameTeam = convertView.findViewById(R.id.tvNameFavoriteTeam);

        star.setImageResource(teams.get(position).isFavorites() ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_32);

        addOnClickStarListener(star, position);
        logo.setImageResource(teams.get(position).getLogo());
        star.setColorFilter(R.color.black);
        tvNameTeam.setText(teams.get(position).getName());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Log.i(LOG_TAG, "HEREE-------------------");
    }

    private void addOnClickStarListener(ImageView star, int position){
        star.setOnClickListener(view -> {
            TeamDml db = new TeamDml(context);
            Team actualTeam = teams.get(position);

            actualTeam.flipFavorite();
            //Display full star + add the team to the DB
            if(actualTeam.isFavorites()){
                star.setImageResource(R.drawable.ic_baseline_star_24);
                db.addLine(actualTeam.getAbreviation());
            }else{ //Display border start + remove team from BD
                star.setImageResource(R.drawable.ic_baseline_star_border_32);
                db.deleteFilteredTableContent(actualTeam.getAbreviation());
            }
        });
    }
}
