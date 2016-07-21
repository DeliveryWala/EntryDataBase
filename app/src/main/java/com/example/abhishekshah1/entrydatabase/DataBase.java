package com.example.abhishekshah1.entrydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abhishek.Shah1 on 7/7/2015.
 */
public class DataBase extends SQLiteOpenHelper{
   public final static String TABLE_NAME = "STUDENT";
    SQLiteDatabase db;
    public static final String KEY_ROWID = "_id";
   final static String DB_NAME= "TestingData.db";
    public static final int DB_VERSION = 1;
    private static Context mContext;

    public   DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("+KEY_ROWID + " Integer PRIMARY KEY autoincrement,STUDENT_NAME Text,FATHER_NAME Text,MOTHER_NAME Text);");

    }

      public void insertIntoTableValue(String StudentName, String FatherName,String MotherName) {
          ContentValues contentValues = new ContentValues();
          contentValues.put("STUDENT_NAME", StudentName);
          contentValues.put("FATHER_NAME", FatherName);
          contentValues.put("MOTHER_NAME", MotherName);
          long id = db.insert(TABLE_NAME, null, contentValues);
    Cursor cursor = db.rawQuery("SELECT * FROM STUDENT", null);
int count = cursor.getCount();

      }
}