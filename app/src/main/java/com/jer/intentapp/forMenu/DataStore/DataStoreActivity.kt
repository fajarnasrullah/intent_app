package com.jer.intentapp.forMenu.DataStore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.jer.intentapp.R

class DataStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        
        val pref = SettingsPreferencesDataStore.getInstance(application.dataStore)
        val viewModel = ViewModelProvider(this, VMFactoryDataStore(pref)).get(VMforDataSotre::class.java)

        switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked ->
            viewModel.saveThemeSetting(isChecked)
        }

        viewModel.getThemeSetting().observe(this) {isDarkmode: Boolean ->
            if (isDarkmode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }


//        val pref = SettingsPreferencesDataStore.getInstance(application.dataStore)
//        val viewModel = ViewModelProvider(this, VMFactoryDataStore(pref)).get(
//            VMforDataSotre::class.java
//        )

//        viewModel.getTheme().observe(this) { isDarkmodeactive: Boolean ->
//            if (isDarkmodeactive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                switchTheme.isChecked = true
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                switchTheme.isChecked = false
//            }
//        }
//
//        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked ->
//            viewModel.saveSetting(isChecked)
//
//        }
    }
}