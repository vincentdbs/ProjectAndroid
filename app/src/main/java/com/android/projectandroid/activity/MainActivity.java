package com.android.projectandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

//    https://android.jlelse.eu/schedule-tasks-and-jobs-intelligently-in-android-e0b0d9201777
//https://code.tutsplus.com/tutorials/android-fundamentals-scheduling-recurring-tasks--mobile-5788
//https://stackoverflow.com/questions/33055129/how-to-show-a-notification-everyday-at-a-certain-time-even-when-the-app-is-close
// todo sensor + service + permission
    //https://www.tutorialspoint.com/how-to-create-everyday-notifications-at-certain-time-in-android
    // cours

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

        myAlarm();

        //todo delete
        createNotificationChannel();
        notif();
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
        switch (item.getItemId()) {
            case R.id.menuItemPlayerStat:
                Intent intentPlayer = new Intent(this, PlayerStatsActivity.class);
                startActivity(intentPlayer);
                return true;

            case R.id.menuItemFavoriteTeam:
                Intent intentFavorite = new Intent(this,  FavoriteTeamActivity.class);
                startActivity(intentFavorite);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void notif(){
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_baseline_star_border_32)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my_channel_notif";
            String description = "my_channel_desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void myAlarm() {
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