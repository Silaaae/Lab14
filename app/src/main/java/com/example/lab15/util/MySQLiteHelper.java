package com.example.lab15.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NOM_BDD = "ecole";

    private static final String CREATE_TABLE =
            "CREATE TABLE etudiant(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT," +
            "prenom TEXT)";

    public MySQLiteHelper(Context context) {
        super(context, NOM_BDD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS etudiant");
        onCreate(db);
    }
}
