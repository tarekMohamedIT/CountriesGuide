package com.example.r3tr0.countriesguide.interactors.managers;

import android.os.AsyncTask;

import com.example.r3tr0.countriesguide.core.events.OnJsonArrayDownloaded;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The class that is responsible for fetching the data from the API url.
 */

public class JsonManager extends AsyncTask<String, Void, JSONArray> {
    OnJsonArrayDownloaded onJsonObjectDownloaded; //The event of process completion.

    /**
     * The constructor of the Json manager class.
     * @param onJsonObjectDownloaded The event handler.
     */
    public JsonManager(OnJsonArrayDownloaded onJsonObjectDownloaded) {
        this.onJsonObjectDownloaded = onJsonObjectDownloaded;
    }

    /**
     * The class's background method.
     *
     * The app opens a connection with the API passed to this method
     * , when the connection is opened, The app receives the Json array from the API
     * then returns it to the UI thread in the OnPostExecute method.
     * @param urls The urls array which is sent to this method via the execute method
     *             (The app will use the first url only).
     *
     * @return A json array object or null if not successful.
     */
    @Override
    protected JSONArray doInBackground(String... urls) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urls[0]).openConnection();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }

            return new JSONArray(builder.toString());

        }

        catch (IOException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        if (onJsonObjectDownloaded != null)
            onJsonObjectDownloaded.onArrayDownloaded(jsonArray);
    }
}
