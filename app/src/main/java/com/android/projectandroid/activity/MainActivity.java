package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.projectandroid.R;
import com.android.projectandroid.adapter.SectionMatchAdapter;
import com.android.projectandroid.fragment.FragmentAllMatch;
import com.android.projectandroid.fragment.FragmentFavoritesMatch;
import com.android.projectandroid.utlis.AlarmReceiver;
import com.android.projectandroid.utlis.utils;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // todo logo app
    // todo readme
    // todo comments + cleanup + git
    // todo sensor + service + permission


    private TextView tv_date;
    private ViewPager vpMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_date = findViewById(R.id.date);
        vpMatch = findViewById(R.id.viewPagerMatch);

        tv_date.setText(utils.getNowDate());

        setupViewPager(vpMatch);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layoutFooter);
        tabLayout.setupWithViewPager(vpMatch);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplication(), R.color.blue));
        setupTabIcons(tabLayout);
        tabSelectedListener(tabLayout);

        actionBar();

        setUpAlarm();
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

    private void setupViewPager(ViewPager viewPager) {
        SectionMatchAdapter adapter = new SectionMatchAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAllMatch(), "one");
        adapter.addFragment(new FragmentFavoritesMatch(), "two");
        viewPager.setAdapter(adapter);
    }

    private void actionBar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.layoutNavbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int menuItemPlayerStat = R.id.menuItemPlayerStat;
        final int menuItemFavoriteTeam = R.id.menuItemFavoriteTeam;
        switch (item.getItemId()) {
            case menuItemPlayerStat:
                Intent intentPlayer = new Intent(this, PlayerStatsActivity.class);
                startActivity(intentPlayer);
                return true;

            case menuItemFavoriteTeam:
                Intent intentFavorite = new Intent(this,  FavoriteTeamActivity.class);
                startActivity(intentFavorite);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpAlarm() {
        //Set the time of the alarm
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        //The class to wake up when the time arrive
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}