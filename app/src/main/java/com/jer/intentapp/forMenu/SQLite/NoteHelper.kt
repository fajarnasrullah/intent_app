package com.jer.intentapp.forMenu.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.jer.intentapp.forMenu.SQLite.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.jer.intentapp.forMenu.SQLite.DatabaseContract.NoteColumns.Companion._ID
import kotlin.Throws


class NoteHelper(context: Context) {

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: NoteHelper? = null

        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NoteHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()

        if (database.isOpen) {
            database.close()
        }
    }

    fun queryAll(): Cursor {
        return database.query(DATABASE_TABLE, null, null, null, null, null, "$_ID ASC")
    }

    fun queryById(id: String): Cursor {
        return database.query(DATABASE_TABLE, null, "$_ID = ?", arrayOf(id), null, null, null, null)
    }

    fun insert(values: ContentValues): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun delete(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }


//    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
//    private lateinit var database: SQLiteDatabase
//
//
//    companion object {
//        private const val DATABASE_TABLE = TABLE_NAME
//        private var INSTANCE: NoteHelper? = null
//
//        fun getInstance(context: Context): NoteHelper =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: NoteHelper(context)
//            }
//    }
//
//    @Throws(SQLException::class)
//    fun open() {
//        database = databaseHelper.writableDatabase
//    }
//
//    fun close() {
//        database.close()
//
//        if (database.isOpen) {
//            database.close()
//        }
//    }
//
//    fun insert(values: ContentValues): Long {
//        return database.insert(DATABASE_TABLE, null, values)
//    }
//
//    fun kueryall(): Cursor {
//        return database.query(DATABASE_TABLE, null, null, null, null, null, "$_ID ASC")
//    }
//
//    fun kueryById(id: String): Cursor {
//        return database.query(DATABASE_TABLE, null,"$_ID = ?",arrayOf(id),null, null,null, null)
//    }
//
//    fun update(id: String, vales: ContentValues): Int{
//        return database.update(DATABASE_TABLE, vales, "$_ID = ?", arrayOf(id) )
//    }
//
//    fun delete(id: String): Int {
//        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
//    }


}