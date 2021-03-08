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
import java.net.URL;

import static com.android.projectandroid.utlis.constants.LOG_TAG;

/**
 * Mother class of asynctask that call an API and returns a JSONObject in the doInBackground method
 */
public class AsyncTaskStringJson extends AsyncTask<String, Void, JSONObject> {
    public AsyncTaskStringJson() {
    }

    /**
     * Do a http request with the URL passed in parameter
     * @param strings, the URL of the http request
     * @return the result of the call in a JSON format
     */
    @Override
    protected JSONObject doInBackground(String... strings) {
        Log.i(LOG_TAG, strings[0]);
        URL url;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                //Do the request
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                //Get the result in a string format
                String s = utils.readStream(in);

                //Return the JSON in string format
                return new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            Log.i(LOG_TAG, e.toString());
        }
        return null;
    }
}
