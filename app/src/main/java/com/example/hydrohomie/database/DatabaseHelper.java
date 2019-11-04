package com.example.hydrohomie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jjoe64.graphview.series.DataPoint;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DrinkHistory.db";
    public static final String TABLE_NAME = "water_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "WATER";
    public static final String COL_4 = "TIME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); // line used to test db creation
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,DATE TEXT ,WATER INTEGER, TIME LONG) ");
    }

    public boolean insertNewDrink(String date, int water, long time) {
        SQLiteDatabase db = this.getWritableDatabase();
        int current;
        //String  selectQuery = "SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = " + date;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_3,COL_4}, "DATE=?", new String[]{date}, null, null, null);
        if (cursor.moveToFirst()) {
            current = cursor.getInt(0);
        } else {
            current = 0;
        }
        ContentValues data = new ContentValues();
        data.put(COL_2,date);
        data.put(COL_3,current + water);
        data.put(COL_4, time);
        String where = "date=?";
        String[] whereArgs = new String[] {String.valueOf(date)};
        int updated = db.update(TABLE_NAME, data, where, whereArgs);
        if(updated == 0) {
            long success = db.insert(TABLE_NAME,null,data);
            if(success == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public DataPoint[] getDataPoint() {
        SQLiteDatabase db = this.getWritableDatabase();
        //int current;
        //String  selectQuery = "SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = " + date;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_4,COL_3}, null, null, null, null, null);

        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for(int i = 0; i<cursor.getCount(); i++) {
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0),cursor.getInt(1));
        }
        return dp;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
