package com.jer.intentapp.forMenu.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jer.intentapp.forMenu.SQLite.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: NoteForRoom)

    @Update
    fun update(note: NoteForRoom)

    @Delete
    fun delete(note: NoteForRoom)

    @Query("SELECT * from NoteForRoom ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteForRoom>>

}