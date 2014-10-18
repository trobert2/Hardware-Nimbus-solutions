package com.solutions.nimbus.doorbell.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.solutions.nimbus.doorbell.DoorbellEntry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arwen on 10/18/2014.
 */
public class DoorbellEntrySource {
    
    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_DATE,
            SQLiteHelper.COLUMN_MESSAGE };

    public DoorbellEntrySource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DoorbellEntry createDoorbellEntry(String date, String message) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_DATE, date);
        values.put(SQLiteHelper.COLUMN_MESSAGE, message);
        long insertId = database.insert(SQLiteHelper.TABLE_LOGS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_LOGS,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DoorbellEntry doorbellEntry = cursorToDoorbellEntry(cursor);
        cursor.close();
        return doorbellEntry;
    }
//
//    public void deleteComment(DoorbellEntry doorbellEntry) {
//        long id = doorbellEntry.getId();
//        System.out.println("Comment deleted with id: " + id);
//        database.delete(SQLiteHelper.TABLE_COMMENTS, SQLiteHelper.COLUMN_ID
//                + " = " + id, null);
//    }

    public List<DoorbellEntry> getAllComments() throws SQLException {
        List<DoorbellEntry> dorbellEntryList = new ArrayList<DoorbellEntry>();
        open();
        Cursor cursor = database.query(SQLiteHelper.TABLE_LOGS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DoorbellEntry doorbellEntry = cursorToDoorbellEntry(cursor);
            dorbellEntryList.add(doorbellEntry);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return dorbellEntryList;
    }

    private DoorbellEntry cursorToDoorbellEntry(Cursor cursor) {
        DoorbellEntry doorbellEntry = new DoorbellEntry();
        doorbellEntry.setId(cursor.getLong(0));
        doorbellEntry.setDate(cursor.getString(1));
        doorbellEntry.setMessage(cursor.getString(2));
        return doorbellEntry;
    }
} 