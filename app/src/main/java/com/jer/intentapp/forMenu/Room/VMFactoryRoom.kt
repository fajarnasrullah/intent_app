package com.jer.intentapp.forMenu.Room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class VMFactoryRoom private constructor(private val application: Application) : ViewModelProvider.NewInstanceFactory() {


    companion object {
        @Volatile
        private var INSTANCE : VMFactoryRoom? = null

        @OptIn(InternalCoroutinesApi::class)
        @JvmStatic
        fun getInstance(application: Application): VMFactoryRoom {
            if (INSTANCE == null) {
                synchronized(VMFactoryRoom::class.java) {
                    INSTANCE = VMFactoryRoom(application)
                }
            }
            return INSTANCE as VMFactoryRoom
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMNoteRoom::class.java)) {
            return VMNoteRoom(application) as T
        } else if (modelClass.isAssignableFrom(VMNoteRoomAdd::class.java)) {
            return VMNoteRoomAdd(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}