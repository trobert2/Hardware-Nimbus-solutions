package com.solutions.nimbus.doorbell.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOGS = "logs1";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_ID = "_id";

    private static final String DATABASE_NAME = "logs1.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LOGS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_DATE
            + " text not null, " + COLUMN_MESSAGE
            + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGS);

        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGS);
        onCreate(db);
    }



}