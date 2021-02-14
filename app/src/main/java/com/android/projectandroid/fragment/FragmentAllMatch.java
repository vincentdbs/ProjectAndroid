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

public class FragmentAllMatch extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_match, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Match> listOfMatches = new ArrayList<>();
        listOfMatches.add(new Match(R.drawable.logo_76ers, R.drawable.logo_bucks, "76ers", "bucks", "112", "113", "Finished"));
        listOfMatches.add(new Match(R.drawable.logo_76ers, R.drawable.logo_bucks, "76ers", "bucks", "112", "113", "Finished"));
        listOfMatches.add(new Match(R.drawable.logo_76ers, R.drawable.logo_bucks, "76ers", "bucks", "112", "113", "Finished"));
        listOfMatches.add(new Match(R.drawable.logo_76ers, R.drawable.logo_bucks, "76ers", "bucks", "112", "113", "Finished"));
        listOfMatches.add(new Match(R.drawable.logo_76ers, R.drawable.logo_bucks, "76ers", "bucks", "112", "113", "Finished"));

        MatchListAdapter adapter = new MatchListAdapter(getContext(), listOfMatches);

        ListView list = (ListView) getActivity().findViewById(R.id.lvAllMatch);

        list.setAdapter(adapter);
    }
}
