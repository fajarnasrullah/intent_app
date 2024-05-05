package com.jer.intentapp.forMenu.Room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class VMNoteRoom(application: Application): ViewModel() {

    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<NoteForRoom>> = mNoteRepository.getAllNotes()

}