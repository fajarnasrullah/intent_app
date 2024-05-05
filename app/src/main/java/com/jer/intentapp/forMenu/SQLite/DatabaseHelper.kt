package com.jer.intentapp.forMenu.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jer.intentapp.forMenu.SQLite.DatabaseContract.NoteColumns.Companion.TABLE_NAME


internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {

        private const val DB_NAME = "dbnoteapp"

        private const val DB_VERSION = 1

        private const val SQL_CREATE_TABLE_NOTE =  "CREATE  TABLE $TABLE_NAME" +
                " (${DatabaseContract.NoteColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.NoteColumns.TITLE} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColumns.DESCRIPTION} TEXT NOT NULL," +
                " ${DatabaseContract.NoteColumns.DATE} TEXT NOT NULL)"

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}