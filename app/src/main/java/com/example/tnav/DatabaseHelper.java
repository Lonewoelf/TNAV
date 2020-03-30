package com.example.tnav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "food_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DBFood.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBFood.TABLE_NAME);

        onCreate(db);
    }

    public long insertFood(String Titel, String Desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBFood.COLUMN_TITEL, Titel);
        values.put(DBFood.COLUMN_DESC, Desc);

        long id = db.insert(DBFood.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public List<DBFood> getAllFood() {
        List<DBFood> food = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DBFood.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DBFood fooddb = new DBFood();
                fooddb.setId(cursor.getInt(cursor.getColumnIndex(DBFood.COLUMN_ID)));
                fooddb.setTitel(cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_TITEL)));
                fooddb.setDesc(cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_DESC)));

                food.add(fooddb);
            } while (cursor.moveToNext());
        }

        db.close();

        return food;
    }


}