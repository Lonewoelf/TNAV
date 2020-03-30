package com.example.tnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.foodlistfragment_layout, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ArrayList<FoodItem> foodList = new ArrayList<>();
        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD", "DOOF"));
        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD2", "DOOF"));
        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD3", "DOOF"));

        mRecyclerView = view.findViewById(R.id.recyclerView_List);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ListAdapter(foodList);
//test
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
