package com.example.proyectobase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transacciones(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sender text, receiver text, amount int)");
        db.execSQL("CREATE TABLE balances(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, address text, icebalance int, usdbalance int)");
        db.execSQL("INSERT INTO balances (address, icebalance, usdbalance) VALUES ('Sebastian', 420, 666)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
