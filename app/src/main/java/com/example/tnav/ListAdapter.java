package com.example.tnav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context context;
    private List<DBFood> foodList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public TextView Desc;

        public MyViewHolder(View view) {
            super(view);
            Title = view.findViewById(R.id.foodTitel);
            Desc = view.findViewById(R.id.foodDesc);
        }
    }


    public ListAdapter(List<DBFood> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DBFood mFood = foodList.get(position);

        holder.Title.setText(mFood.getTitle());
        holder.Desc.setText(mFood.getDesc());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}