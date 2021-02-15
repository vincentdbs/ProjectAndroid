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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_team, viewGroup, false);
        }

        ImageView logo = convertView.findViewById(R.id.ivLogoTeamFavorite);
        ImageView star = convertView.findViewById(R.id.ivStar);
        TextView tvNameTeam = convertView.findViewById(R.id.tvNameFavoriteTeam);

        star.setImageResource(teams.get(i).isFavorites() ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_32);
        star.setOnClickListener(view -> {
            teams.get(i).flipFavorite();
            star.setImageResource(teams.get(i).isFavorites() ? R.drawable.ic_baseline_star_24 : R.drawable.ic_baseline_star_border_32);
        });

        logo.setImageResource(teams.get(i).getLogo());
        star.setColorFilter(R.color.black);
        tvNameTeam.setText(teams.get(i).getName());

        return convertView;
    }
}
