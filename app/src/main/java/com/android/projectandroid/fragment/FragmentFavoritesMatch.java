package com.android.projectandroid.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.MatchListAdapter;
import com.android.projectandroid.asynctask.AsyncTaskMatch;
import com.android.projectandroid.database.TeamDml;
import com.android.projectandroid.model.Match;
import com.android.projectandroid.utlis.utils;

import java.util.ArrayList;
import java.util.Calendar;

import static com.android.projectandroid.utlis.constants.LOG_TAG;
import static com.android.projectandroid.utlis.constants.MAP_LOGO_TEAM;

public class FragmentFavoritesMatch extends Fragment{
    private MatchListAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites_match, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView list =  getActivity().findViewById(R.id.lvFavoritesMatch);

        adapter = new MatchListAdapter(getContext());
        list.setAdapter(adapter);

        String paramDate = utils.getNowDate();
        new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?start_date=" + paramDate+ "&end_date=" + paramDate + getParamArrayOfApiTeamId());
    }

    @Override
    public void onResume() {
        Log.i(LOG_TAG, "Resume fragment");
        super.onResume();
        String paramDate = utils.getNowDate();
        TextView tv_date = getActivity().findViewById(R.id.date);
        new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?start_date=" + tv_date.getText().toString() + "&end_date=" + tv_date.getText().toString() + getParamArrayOfApiTeamId());
    }

    private String getParamArrayOfApiTeamId(){
        TeamDml db = new TeamDml(getContext());
        ArrayList<String> listOfFav = db.getAllFavTeamAbrev();
        String param = "";
        for (String str: listOfFav) {
            param += "&team_ids[]=" + MAP_LOGO_TEAM.get(str).getApiId();
        }

        return param.isEmpty() ? "&team_ids[]=0" : param;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemCalendar:  {
                setDateOnClickitem();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDateOnClickitem(){
        // User chose the "Settings" item, show the app settings UI...
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            // Set the calendar to the selected day
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            // Set the text view to the selected date
            TextView tv_date = getActivity().findViewById(R.id.date);
            tv_date.setText(year+ "-" + month + "-" + day);
            new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?start_date=" + tv_date.getText().toString() + "&end_date=" + tv_date.getText().toString() + getParamArrayOfApiTeamId());

        };

        new DatePickerDialog(
                getActivity(), date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
