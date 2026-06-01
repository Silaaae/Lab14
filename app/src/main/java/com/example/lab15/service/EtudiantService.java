package com.example.lab15.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.util.MySQLiteHelper;

public class EtudiantService {

    private static final String TABLE = "etudiant";
    private final MySQLiteHelper helper;

    public EtudiantService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public void create(Etudiant e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", e.getNom());
        values.put("prenom", e.getPrenom());
        db.insert(TABLE, null, values);
        db.close();
    }

    public Etudiant findById(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(TABLE, new String[]{"id", "nom", "prenom"},
                "id = ?", new String[]{String.valueOf(id)},
                null, null, null);
        Etudiant e = null;
        if (c.moveToFirst()) {
            e = new Etudiant();
            e.setId(c.getInt(0));
            e.setNom(c.getString(1));
            e.setPrenom(c.getString(2));
        }
        c.close();
        db.close();
        return e;
    }

    public void delete(Etudiant e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public void update(Etudiant e) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", e.getNom());
        values.put("prenom", e.getPrenom());
        db.update(TABLE, values, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    public List<Etudiant> findAll() {
        List<Etudiant> liste = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE, null);
        if (c.moveToFirst()) {
            do {
                Etudiant e = new Etudiant();
                e.setId(c.getInt(0));
                e.setNom(c.getString(1));
                e.setPrenom(c.getString(2));
                liste.add(e);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return liste;
    }
}
