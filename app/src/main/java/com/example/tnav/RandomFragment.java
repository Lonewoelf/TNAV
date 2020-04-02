package com.example.tnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tnav.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RandomFragment extends Fragment {
    String responseText = "Default";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.randomfragment_layout, container, false);
        URL url = null;
        try {
            url = new URL("https://api.icndb.com/jokes/random");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new JsonTask(view).execute(url);

        return view;
    }
}
