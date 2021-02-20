package com.android.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.model.Match;

import java.util.ArrayList;

public class MatchListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Match> matches;

    public MatchListAdapter(Context context, ArrayList<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    public MatchListAdapter(Context context) {
        this.context = context;
        this.matches = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Object getItem(int i) {
        return matches.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_match, viewGroup, false);
        }

        ImageView imTeamDom = convertView.findViewById(R.id.logoTeamDom);
        ImageView imTeamExt = convertView.findViewById(R.id.logoTeamExt);
        TextView tvNameTeamDom = convertView.findViewById(R.id.nameTeamDom);
        TextView tvNameTeamExt = convertView.findViewById(R.id.nameTeamExt);
        TextView tvScoreTeamDom = convertView.findViewById(R.id.scoreTeamDom);
        TextView tvScoreTeamExt = convertView.findViewById(R.id.scoreTeamExt);
        TextView time = convertView.findViewById(R.id.time);

        imTeamDom.setImageResource(matches.get(i).getIdLogoTeamDom());
        imTeamExt.setImageResource(matches.get(i).getIdLogoTeamExt());
        tvNameTeamDom.setText(matches.get(i).getNameTeamDom());
        tvNameTeamExt.setText(matches.get(i).getNameTeamExt());
        tvScoreTeamDom.setText(matches.get(i).getScoreTeamDom());
        tvScoreTeamExt.setText(matches.get(i).getScoreTeamExt());
        time.setText(matches.get(i).getTime());


        return convertView;
    }

    public void add(Match match){
        matches.add(match);
    }

    public void clearMatches(){ matches.clear();}
}
