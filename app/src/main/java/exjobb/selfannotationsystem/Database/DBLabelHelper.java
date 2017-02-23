package exjobb.selfannotationsystem.Database;

/**
 * Created by Andréas Appelqvist on 2017-02-23.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class DBLabelHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "labels.db";
    public static final String TABLE_LABELS = "labels";
    public static final String COLUMN_ID = "L_id";
    private static final String COLUMN_VALUE = "value";

    public DBLabelHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LABELS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_VALUE + " TEXT "+
                ");";
        db.execSQL(query);
        String[] defaults = {"No label","Transportsträcka", "Stressad aktivitet", "Omedveten aktivitet", "Jobbaktivitet", "Skolgympa"};
        for(String s : defaults){
            addLabel(s);
        }
    }

    public void addLabel(String text){
        ContentValues values = new ContentValues();
        values.put(COLUMN_VALUE, text);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_LABELS);
        onCreate(db);
    }
}