package com.jer.intentapp.forMenu.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


 val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Settings")

class SettingsPreferencesDataStore private constructor(private val dataStore: DataStore<Preferences>) {


    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getTheme() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveTheme(isDarkMode:Boolean) {
        dataStore.edit {preferences ->
            preferences[THEME_KEY] = isDarkMode
        }
    }

    companion object {
        @Volatile

        private var INSTANCE : SettingsPreferencesDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>) : SettingsPreferencesDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPreferencesDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }






























//    private val THEME_KEY = booleanPreferencesKey("theme_setting")
//
//    fun getThemeSetting(): Flow<Boolean> {
//        return dataStore.data.map { preferences ->
//            preferences[THEME_KEY] ?: false
//        }
//    }
//
//    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[THEME_KEY] = isDarkModeActive
//        }
//    }
//
//
//
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SettingsPreferencesDataStore? = null
//
//        fun getInstance(dataStore: DataStore<Preferences>): SettingsPreferencesDataStore {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = SettingsPreferencesDataStore(dataStore)
//                INSTANCE = instance
//                instance
//            }
//
//        }
//    }

}