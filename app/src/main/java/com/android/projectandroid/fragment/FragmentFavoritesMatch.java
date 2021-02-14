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
import com.android.projectandroid.model.Match;

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

        ArrayList<Match> listOfMatches = new ArrayList<>();
        listOfMatches.add(new Match(R.drawable.logo_spurs, R.drawable.logo_pacers, "Spurs", "Pacers", "96", "82", "10AM"));

        MatchListAdapter adapter = new MatchListAdapter(getContext(), listOfMatches);

        ListView list = (ListView) getActivity().findViewById(R.id.lvFavoritesMatch);

        list.setAdapter(adapter);
    }
}
