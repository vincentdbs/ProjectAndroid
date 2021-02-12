package com.android.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.projectandroid.R;
import com.android.projectandroid.asynctask.AsyncTaskMatch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTaskMatch().execute("https://www.balldontlie.io/api/v1/games");

    }
}