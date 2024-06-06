package com.example.todolist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class tamamlananGorevListVeriTabaniYardimcisi(mContext: Context):SQLiteOpenHelper(mContext,"tamamlananGorevList",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE tamamlananGorevList(gorevId INTEGER ,gorevIsim TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tamamlananGorevList",null)
        onCreate(db)
    }
}