package com.example.tnav;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.foodViewHolder> {
    private ArrayList<DBFood> mFoodList;

    public static class foodViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public foodViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.foodImage);
            mTextView1 = itemView.findViewById(R.id.foodTitel);
            mTextView2 = itemView.findViewById(R.id.foodDesc);
        }
    }

    public ListAdapter(ArrayList<DBFood> exampleList) {
        mFoodList = exampleList;
    }

    @Override
    public foodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        foodViewHolder fvh = new foodViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(foodViewHolder holder, int position) {
        DBFood currentItem = mFoodList.get(position);

        //holder.mImageView.setImageResource(currentItem.getImage());
        holder.mTextView1.setText(currentItem.getDesc());
        holder.mTextView2.setText(currentItem.getTitel());
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}