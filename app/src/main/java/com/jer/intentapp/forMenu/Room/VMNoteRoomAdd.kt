package com.jer.intentapp.forMenu.Room

import android.app.Application
import androidx.lifecycle.ViewModel

class VMNoteRoomAdd(application: Application): ViewModel() {

    private val mNoteRepository : NoteRepository = NoteRepository(application)

    fun insert(note: NoteForRoom) {
        mNoteRepository.insert(note)
    }

    fun delete(note: NoteForRoom) {
        mNoteRepository.delete(note)
    }

    fun update(note: NoteForRoom) {
        mNoteRepository.update(note)
    }
}