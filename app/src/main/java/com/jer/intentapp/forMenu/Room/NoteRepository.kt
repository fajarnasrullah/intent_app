package com.jer.intentapp.forMenu.Room

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private val mNotesDao: NoteDao
    private val executorService : ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<NoteForRoom>> = mNotesDao.getAllNotes()

    fun insert(note: NoteForRoom) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }

    fun delete(note: NoteForRoom) {
        executorService.execute{
            mNotesDao.delete(note)
        }
    }

    fun update(note: NoteForRoom) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }
}