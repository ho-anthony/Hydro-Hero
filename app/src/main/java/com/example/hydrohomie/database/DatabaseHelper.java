package com.example.hydrohomie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

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

    public DataPoint[] getDataPoint(int index, int size) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_4,COL_3}, null, null, null, null, null);
        DataPoint[] dp;
        cursor.move(index);
        dp = new DataPoint[size];
        for(int i = 0; i<size; i++) {
            cursor.moveToNext();
            dp[i] = new DataPoint(cursor.getInt(0),cursor.getInt(1));
        }

        return dp;
    }

    public int numberOfDates(int index) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_2}, null, null, null, null, null);
        cursor.move(index);
        int dates = cursor.getCount();
        if(dates > 7) {
            dates = 7;
        }
        return dates;
    }

    public ArrayList<String> getAllDates() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_2}, null, null, null, null, null);
        cursor.moveToNext();
        for(int i = 0; i<cursor.getCount() - 6; i+=7) {
            list.add(cursor.getString(0));
            cursor.move(7);
        }
        return list;
    }

    public int totalHydration(int index) {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_3}, null, null, null, null, null);
        cursor.move(index);
        int values = 7;
        if(index == 0) {
            values = 6;
        }
        for(int i = 0; i <= values; i++) {
            cursor.moveToNext();
            total += cursor.getInt(0);
        }
        return total;

    }
  
    public int getLoggedValue() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_3},null, null, null, null, null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
