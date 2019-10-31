package com.example.hydrohomie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DrinkHistory.db";
    public static final String TABLE_NAME = "water_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "WATER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); // line used to test db creation
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,DATE TEXT ,WATER INTEGER) ");
    }

    public boolean insertNewDrink(String date, int water) {
        SQLiteDatabase db = this.getWritableDatabase();
        int current;
        //String  selectQuery = "SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = " + date;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_3}, "DATE=?", new String[]{date}, null, null, null);
        if (cursor.moveToFirst()) {
            current = cursor.getInt(0);
        } else {
            current = 0;
        }
        ContentValues data = new ContentValues();
        data.put(COL_2,date);
        data.put(COL_3,current + water);
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


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
