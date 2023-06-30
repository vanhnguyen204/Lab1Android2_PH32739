package com.fpoly.lab1android2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    Context context;
    public DbHelper(@Nullable Context context) {
        super(context, "vieclam.db", null, 1);
        this.context = context;
    }
    String tableTodo =
            "CREATE TABLE ToDo (ID INTEGER PRIMARY KEY AUTOINCREMENT,Title text, Content text, Date text, Type text, Status INTEGER )";
//String insertVl = "INSERT INTO ToDo Values(1,'Học Java','Học Java cơ bản','20/9/2023','Dễ',1),(2,'Học C++','Học C++ cơ bản','20/9/2023','Khó',0),(3,'Học Python','Học Python cơ bản','20/9/2023','Dễ',1)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableTodo);
//        db.execSQL(insertVl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
if (oldVersion != newVersion){
    db.execSQL("DROP TABLE IF EXISTS ToDo");
    onCreate(db);
}
    }
}
