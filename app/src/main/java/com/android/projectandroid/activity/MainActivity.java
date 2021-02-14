package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.SectionMatchAdapter;
import com.android.projectandroid.asynctask.AsyncTaskMatch;
import com.android.projectandroid.fragment.FragmentAllMatch;
import com.android.projectandroid.fragment.FragmentFavoritesMatch;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class MainActivity extends AppCompatActivity {
//    TODO faire une classe asynctask mére et heritahe pour celle construite par moi avec overide de la methode on postexecute
    //todo change default date à cote du calendar
    private ImageView svgCalendar;
    private TextView tv_date;
    private ViewPager vpMatch;

    //https://codinginflow.com/tutorials/android/tab-layout-with-fragments
    //https://medium.com/@royanimesh2211/swipeable-tab-layout-using-view-pager-and-fragment-in-android-ea62f839502b
    //https://stackoverflow.com/questions/15932975/complex-items-in-a-listview/15933181
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svgCalendar = findViewById(R.id.svgCalendar);
        tv_date = findViewById(R.id.date);
        vpMatch = findViewById(R.id.viewPagerMatch);
        setupViewPager(vpMatch);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layoutFooter);
        tabLayout.setupWithViewPager(vpMatch);



    }

    private void addListenerOnClickCalendar(){
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, year, month, day) -> {
            // Set the calendar to the selected day
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            // Set the text view to the selected date
            tv_date.setText(day + "/" + month + "/" + year);
        };

        svgCalendar.setOnClickListener(view -> new DatePickerDialog(
                MainActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionMatchAdapter adapter = new SectionMatchAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAllMatch(), "fkjshfk");
        adapter.addFragment(new FragmentFavoritesMatch(), "fkdjgh");
        viewPager.setAdapter(adapter);
    }
}