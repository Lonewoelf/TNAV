package com.example.tnav;

import android.database.sqlite.SQLiteDatabase;
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

    private DatabaseHelper dbHelper;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.foodlistfragment_layout, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_List);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<DBFood> foodList = new ArrayList<DBFood>();

        foodList.addAll(dbHelper.getAllFood());

//        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD", "DOOF"));
//        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD2", "DOOF"));
//        foodList.add(new FoodItem(R.drawable.ic_fingerprint, "FOOD3", "DOOF"));

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ListAdapter(foodList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);

        return view;
    }
}
