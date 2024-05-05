package com.jer.intentapp.forMenu.SettingsPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jer.intentapp.R

class SettingsPreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_preferences)

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferencesFragment()).commit()


    }
}