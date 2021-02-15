package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.SectionMatchAdapter;
import com.android.projectandroid.fragment.FragmentAllMatch;
import com.android.projectandroid.fragment.FragmentFavoritesMatch;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
//    TODO faire une classe asynctask mére et heritahe pour celle construite par moi avec overide de la methode on postexecute
    //todo change default date à cote du calendar
    //todo icon n'apparaissent pas dans les tablayout
    //todo changer logo de l'app
    //use real toolbar component in main activity
    //todo use arrayadapter instead of baseadapter

    private ImageView svgCalendar, svgMenu;
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
        svgMenu = findViewById(R.id.svgMenu);

        setupViewPager(vpMatch);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layoutFooter);
        tabLayout.setupWithViewPager(vpMatch);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplication(), R.color.blue));
        setupTabIcons(tabLayout);
        tabSelectedListener(tabLayout);

        addOnClickListenerCalendar();
        addOnClickListenerMenu();
    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_sports_basketball_32);
        tabLayout.getTabAt(0).getIcon().setTint(ContextCompat.getColor(getApplication(), R.color.blue));
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_star_border_32);
    }

    private void tabSelectedListener(TabLayout tabLayout){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.getIcon().setTint(ContextCompat.getColor(getApplication(), R.color.blue));
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplication(), R.color.blue));
                        break;
                    case 1:
                        tab.getIcon().setTint(ContextCompat.getColor(getApplication(), R.color.red));
                        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplication(), R.color.red));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setTint(ContextCompat.getColor(getApplication(), R.color.black));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addOnClickListenerCalendar(){
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

    private void addOnClickListenerMenu(){
        svgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerStatsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionMatchAdapter adapter = new SectionMatchAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAllMatch(), "one");
        adapter.addFragment(new FragmentFavoritesMatch(), "two");
        viewPager.setAdapter(adapter);
    }
}