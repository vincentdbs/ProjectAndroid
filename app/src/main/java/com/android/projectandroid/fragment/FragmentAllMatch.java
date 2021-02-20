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

public class FragmentAllMatch extends Fragment {
    private MatchListAdapter adapter;
    private TextView calendar;
    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_match, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get the view
        calendar = getActivity().findViewById(R.id.date);
        adapter = new MatchListAdapter(getContext());
        list = (ListView) getActivity().findViewById(R.id.lvAllMatch);
        list.setAdapter(adapter);

        //Call asynctask
        String param = utils.getNowDate();
        callAsyncTask("start_date=" + param+ "&end_date=" + param);
    }

    @Override
    public void onResume() {
        //When the fragment is seen by the user
        super.onResume();
        callAsyncTask("start_date=" + calendar.getText().toString() + "&end_date=" + calendar.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemCalendar) {
            setDateOnClickItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void callAsyncTask(String param){
        new AsyncTaskMatch(adapter).execute("https://www.balldontlie.io/api/v1/games?" + param);
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
            callAsyncTask("start_date=" + calendar.getText().toString() + "&end_date=" + calendar.getText().toString());
        };

        new DatePickerDialog(
                getActivity(), date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
