package com.jer.intentapp.forMenu.DataStore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VMFactoryDataStore(private val pref: SettingsPreferencesDataStore): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VMforDataSotre::class.java)) {
            return VMforDataSotre(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}