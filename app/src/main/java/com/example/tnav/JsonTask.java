package com.example.tnav;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends AsyncTask<URL, String, String> {

    private View rootView;
    String joke = null;

    String url2 = "https://api.icndb.com/jokes/random";

    public JsonTask(View rootView) {
        this.rootView = rootView;
    }

    @Override
    protected String doInBackground(URL... urls) {
        URL url = urls[0];
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setReadTimeout(5000);
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            joke = sb.toString();

            JSONObject mainObject = new JSONObject(joke);
            JSONObject value = mainObject.getJSONObject("value");
            String joke = value.getString("joke");

            return joke;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Joke failed";
    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

    protected void onPostExecute(String joke) {
        TextView display = rootView.findViewById(R.id.quote);
        display.setText(joke);
    }
}
