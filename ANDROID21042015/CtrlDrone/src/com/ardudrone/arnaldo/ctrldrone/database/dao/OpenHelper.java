package com.ardudrone.arnaldo.ctrldrone.database.dao;

import org.droidpersistence.dao.TableDefinition;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper  extends SQLiteOpenHelper {
    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            TableDefinition.onCreate(db);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            TableDefinition.onUpgrade(db, oldVersion, newVersion);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
