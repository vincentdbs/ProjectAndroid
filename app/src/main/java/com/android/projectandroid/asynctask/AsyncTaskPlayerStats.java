package com.android.projectandroid.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.android.projectandroid.utlis.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

public class AsyncTaskPlayerStats extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url;
        try {
            //Replace http by https
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = utils.readStream(in);

                //Get the authenticated value
                JSONObject result = new JSONObject(s);
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.i(LOG_TAG, e.toString());
        } catch (IOException e) {
            Log.i(LOG_TAG, e.toString());
        }
        return null;
    }
}
