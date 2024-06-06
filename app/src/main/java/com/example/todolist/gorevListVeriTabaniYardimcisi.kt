package com.example.todolist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class gorevListVeriTabaniYardimcisi(mContext:Context):SQLiteOpenHelper(mContext,"gorevList",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE gorevList(gorevId INTEGER PRIMARY KEY AUTOINCREMENT,gorevIsim TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS gorevList",null)
        onCreate(db)
    }
}