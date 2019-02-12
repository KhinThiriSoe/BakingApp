package com.khinthirisoe.bakingapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Singleton

@Singleton
class AppPreferencesHelper constructor(context: Context) : PreferencesHelper {

    private val mPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override var recipeName: String
        get() = mPrefs.getString(PREF_KEY_RECIPE_NAME, "Ingredients")
        set(value) {
            mPrefs.edit().putString(PREF_KEY_RECIPE_NAME, value).apply()
        }

    companion object {
        private const val PREF_KEY_RECIPE_NAME = "recipe_name"
    }
}
