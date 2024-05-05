package com.jer.intentapp.forMenu.DataStore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VMforDataSotre(private val pref: SettingsPreferencesDataStore): ViewModel() {



    fun getThemeSetting(): LiveData<Boolean>{
        return pref.getTheme().asLiveData()
    }

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            pref.saveTheme(isDarkMode)
        }
    }





//    fun getTheme(): LiveData<Boolean> {
//        return pref.getThemeSetting().asLiveData()
//    }
//
//    fun saveSetting(isDarkModeActive: Boolean) {
//        viewModelScope.launch {
//            pref.saveThemeSetting(isDarkModeActive)
//        }
//    }

}