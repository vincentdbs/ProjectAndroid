package com.android.projectandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.asynctask.AsyncTaskMatch;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.utlis.utils;

import java.util.ArrayList;

public class FragmentFavoritesMatch extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_match, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //todo recupere les matchs des équipes favorites et non de toutes les équipes
        MatchListAdapter adapter = new MatchListAdapter(getContext());

        ListView list = (ListView) getActivity().findViewById(R.id.lvFavoritesMatch);

        list.setAdapter(adapter);

        String param = utils.getNowDate();
        new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?start_date=" + param+ "&end_date=" + param);
    }
}
