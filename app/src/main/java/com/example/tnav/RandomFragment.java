package com.example.tnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tnav.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RandomFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.randomfragment_layout, container, false);
    }
}
