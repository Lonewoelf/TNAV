package com.example.tnav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.tnav.DBFood.COLUMN_DESC;
import static com.example.tnav.DBFood.COLUMN_ID;
import static com.example.tnav.DBFood.COLUMN_TITEL;
import static com.example.tnav.DBFood.DB_NAME;
import static com.example.tnav.DBFood.TABLE_NAME;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = DB_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITEL +
                " VARCHAR, " + COLUMN_DESC +
                " VARCHAR" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertFood(String Titel, String Desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBFood.COLUMN_TITEL, Titel);
        values.put(DBFood.COLUMN_DESC, Desc);

        long id = db.insert(TABLE_NAME, null, values);

        db.close();
        return id;
    }

    public List<DBFood> getAllFood() {
        List<DBFood> food = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DBFood fooddb = new DBFood();
                fooddb.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                fooddb.setTitle(cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_TITEL)));
                fooddb.setDesc(cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_DESC)));

                food.add(fooddb);
            } while (cursor.moveToNext());
        }

        db.close();

        return food;
    }

    public DBFood getFood(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, DBFood.COLUMN_TITEL, DBFood.COLUMN_DESC},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        DBFood food = new DBFood(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_TITEL)),
                cursor.getString(cursor.getColumnIndex(DBFood.COLUMN_DESC)));

        cursor.close();

        return food;
    }

    public int updateFood(DBFood food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBFood.COLUMN_TITEL, food.getTitle());
        values.put(DBFood.COLUMN_DESC, food.getDesc());

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(food.getId())});
    }

    public void deleteFood() {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "DELETE  FROM " + TABLE_NAME;
        db.rawQuery(selectQuery, null);
        db.close();
    }
}