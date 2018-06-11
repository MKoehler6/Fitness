package com.example.micha.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 09.01.2018.
 */

public class ToDoDB  extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "todosport";
    public static final String COLNAME_TITLE = "title";
    public static final String _ID = "_id";
    public static final String COLNAME_ISDONE = "isDone";
    public static final String COLNAME_TURNUS = "turnus";
    public static final String COLNAME_PAUSEN = "pausen";
    public static final String COLNAME_SAVEDEVENTS = "savedEvents";

    public  static final int DATABASE_VERSION = 1;
    public static  final String DATABASE_NAME = "TodoSport.db";



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLNAME_TITLE + " TEXT," +
                    COLNAME_ISDONE + " TINYINT(1)," +
                    COLNAME_TURNUS + " TINYINT(1)," +
                    COLNAME_PAUSEN + " TINYINT(1)," +
                    COLNAME_SAVEDEVENTS + " TEXT" + ")";

    private static final String TABLE_TODO_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ToDoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = TABLE_TODO_DROP;
        db.execSQL(dropTable);
        onCreate(db);
    }

    private Context context;
    private  static ToDoDB myInstance;

    public static  ToDoDB getMyInstance(Context context){
        if (myInstance == null){
            myInstance = new ToDoDB(context);
        }
        return myInstance;
    }

    /**public List<Aufgabe> getAll() {
        //TODO
        return new ArrayList<>();
    }**/

    public Aufgabe createAufgabe(Aufgabe aufgabe){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLNAME_TITLE, aufgabe.gibName());
        return null;
    }

    public List<Aufgabe> readAufgabeAlle(){
        SQLiteDatabase db = this.getReadableDatabase();
        //String nameAufgabe = new String();

        try {

            ArrayList<Aufgabe> result = new ArrayList<>();

            Cursor cursor = db.query(TABLE_NAME,
                    new String[]{_ID, COLNAME_TITLE, COLNAME_PAUSEN, COLNAME_TURNUS, COLNAME_ISDONE, COLNAME_SAVEDEVENTS},
                    null, null, null, null, COLNAME_TURNUS);

            try {
                while (cursor.moveToNext()) {
                    Aufgabe aufgabe = new Aufgabe(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5));
                    result.add(aufgabe);
                }

                return result;

            } finally {
                cursor.close();
            }

        } finally {
            db.close();
        }
    }

    public List<Aufgabe> readAufgabeUnerledigt(){
        SQLiteDatabase db = this.getReadableDatabase();
        //String nameAufgabe = new String();

        try {
            //String where = COLNAME_ISDONE + " = ?" + " OR " + COLNAME_ISDONE + " = ?";
            //String[] whereArgs = { "0", "1" };

            String where = COLNAME_ISDONE + " = ?";
            String[] whereArgs = { "0" };

            ArrayList<Aufgabe> result = new ArrayList<>();

            Cursor cursor = db.query(TABLE_NAME,
                    new String[]{_ID, COLNAME_TITLE, COLNAME_PAUSEN, COLNAME_TURNUS, COLNAME_ISDONE, COLNAME_SAVEDEVENTS},
                    where, whereArgs, null, null, COLNAME_TURNUS);

            try {
                while (cursor.moveToNext()) {
                    Aufgabe aufgabe = new Aufgabe(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), "2 14 4 18;4 14 4 18;7 14 3 18;3 13 3 18;2 12 3 18");
                    result.add(aufgabe);
                }

                return result;

            } finally {
                cursor.close();
            }

        } finally {
            db.close();
        }
    }

    public void insert(String name, int pausen, int turnus,  int erledigt){
        long rowId = -1;
        SQLiteDatabase database = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_TITLE, name);
            values.put(COLNAME_PAUSEN, pausen);
            values.put(COLNAME_TURNUS, turnus);
            values.put(COLNAME_ISDONE, erledigt);

            rowId = database.insert(TABLE_NAME, null, values);
        } finally {
            database.close();
        }
    }

    public int setDone(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 1);
            String whereClause = _ID + " = ?";
            String[] whereArgs = { id };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public int setUndone(String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = _ID + " = ?";
            String[] whereArgs = { id };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }

    public int setDailyUndone() {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = COLNAME_TURNUS + " = ?";
            String[] whereArgs = { "1" };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public int setMonthlyUndone1() {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = COLNAME_TURNUS + " = ?" + " and " + COLNAME_PAUSEN + " =? ";
            String[] whereArgs = { "3", "1" };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public int setMonthlyUndone2() {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = COLNAME_TURNUS + " = ?" + " and " + COLNAME_PAUSEN + " =? ";
            String[] whereArgs = { "3", "2" };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public int setMonthlyUndone3() {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = COLNAME_TURNUS + " = ?" + " and " + COLNAME_PAUSEN + " =? ";
            String[] whereArgs = { "3", "3" };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public int setWeeklyUndone() {
        SQLiteDatabase database = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(COLNAME_ISDONE, 0);
            String whereClause = COLNAME_TURNUS + " = ?";
            String[] whereArgs = { "2" };

            return database.update(TABLE_NAME, values, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }

    public int delete(String id) {

        SQLiteDatabase database = this.getWritableDatabase();

        try {
            String whereClause = _ID + " = ?";
            String[] whereArgs = { id };

            return database.delete(TABLE_NAME, whereClause, whereArgs);

        } finally {
            database.close();
        }
    }
    public void deleteAll(){

    }

}
