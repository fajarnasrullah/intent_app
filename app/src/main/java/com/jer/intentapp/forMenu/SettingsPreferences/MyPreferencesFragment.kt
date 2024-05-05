package com.jer.intentapp.forMenu.SettingsPreferences

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.jer.intentapp.R

class MyPreferencesFragment: PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var NAME: String
    private lateinit var AGE: String
    private lateinit var EMAIL: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    private lateinit var namePreferences: EditTextPreference
    private lateinit var agePreferences: EditTextPreference
    private lateinit var emailPreferences: EditTextPreference
    private lateinit var phonePreferences: EditTextPreference
    private lateinit var lovePreferences: CheckBoxPreference

    companion object {
        const val DEFAULT_VALUE = "Tidak ada"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun init() {
        NAME = resources.getString(R.string.key_name)
        AGE = resources.getString(R.string.key_age)
        EMAIL = resources.getString(R.string.key_email)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)

        namePreferences = findPreference<EditTextPreference> (NAME) as EditTextPreference
        agePreferences = findPreference<EditTextPreference>(AGE) as EditTextPreference
        emailPreferences = findPreference<EditTextPreference> (EMAIL) as EditTextPreference
        phonePreferences = findPreference<EditTextPreference>(PHONE) as EditTextPreference
        lovePreferences = findPreference<CheckBoxPreference> (LOVE) as CheckBoxPreference
    }


    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePreferences.summary = sh?.getString(NAME, DEFAULT_VALUE)
        agePreferences.summary = sh?.getString(AGE, DEFAULT_VALUE)
        emailPreferences.summary = sh?.getString(EMAIL, DEFAULT_VALUE)
        phonePreferences.summary = sh?.getString(PHONE, DEFAULT_VALUE)
        lovePreferences.isChecked = sh!!.getBoolean(LOVE, false)
    }

    override fun onResume() {
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
        super.onResume()
    }

    override fun onPause() {
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
        super.onPause()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == NAME) {
            namePreferences.summary = sharedPreferences.getString(NAME, DEFAULT_VALUE)
        }
        if (key == AGE) {
            agePreferences.summary = sharedPreferences.getString(AGE, DEFAULT_VALUE)
        }
        if (key == EMAIL) {
            emailPreferences.summary = sharedPreferences.getString(EMAIL, DEFAULT_VALUE)
        }
        if (key == PHONE) {
            phonePreferences.summary = sharedPreferences.getString(PHONE, DEFAULT_VALUE)
        }
        if (key == LOVE) {
            lovePreferences.isChecked = sharedPreferences.getBoolean(LOVE, false)
        }

    }
}