package com.android.projectandroid.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.android.projectandroid.utlis.utils;

import java.util.Calendar;

public class FragmentFavoritesMatch extends Fragment{
    private MatchListAdapter adapter;
    private TextView calendar;

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

        calendar = getActivity().findViewById(R.id.date);
        ListView list = getActivity().findViewById(R.id.lvFavoritesMatch);
        adapter = new MatchListAdapter(getContext());
        list.setAdapter(adapter);

        String paramDate = utils.getNowDate();
        callAsyncTask("start_date=" + paramDate+ "&end_date=" + paramDate + utils.getParamArrayOfApiTeamId(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        callAsyncTask("start_date=" + calendar.getText().toString() + "&end_date=" + calendar.getText().toString() + utils.getParamArrayOfApiTeamId(getContext()));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemCalendar) {
            setDateOnClickItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDateOnClickItem(){
        // User chose the "Settings" item, show the app settings UI...
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            // Set the calendar to the selected day
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH + 1, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            // Set the text view to the selected date
            calendar.setText(year+ "-" + (month+1) + "-" + day);
            callAsyncTask("start_date=" + calendar.getText().toString() + "&end_date=" + calendar.getText().toString() + utils.getParamArrayOfApiTeamId(getContext()));
        };

        new DatePickerDialog(
                getActivity(), date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void callAsyncTask(String param){
        new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?" + param);
    }

}
