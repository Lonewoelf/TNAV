package com.example.tnav;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper db;
    private List<DBFood> foodList = new ArrayList<>();

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.foodlistfragment_layout, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView_List);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = new DatabaseHelper(getContext());
        foodList.addAll(db.getAllFood());

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFoodDialog(false, null, -1);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ListAdapter(foodList);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void showFoodDialog(final boolean shouldUpdate, final DBFood food, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View viewDialog = layoutInflaterAndroid.inflate(R.layout.edit_dialog_layout, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(viewDialog);

        final EditText inputFoodTitle = viewDialog.findViewById(R.id.editTitle);
        final EditText inputFoodDesc = viewDialog.findViewById(R.id.EditDesc);

        TextView dialogTitle = viewDialog.findViewById(R.id.TextTitle);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.textTitle) : getString(R.string.editTitle));
        dialogTitle.setText(!shouldUpdate ? getString(R.string.textDesc) : getString(R.string.editDesc));

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputFoodTitle.getText().toString()) || TextUtils.isEmpty(inputFoodDesc.getText().toString())) {
                    Toast.makeText(getContext(), "Please fill out both fields!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }
                db.insertFood(inputFoodTitle.getText().toString(), inputFoodDesc.getText().toString());
                foodList.clear();
                foodList.addAll(db.getAllFood());
                mAdapter.notifyDataSetChanged();

            }
        });
    }
}
