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

    override var isLargeScreen: Boolean
        get() = mPrefs.getBoolean(PREF_KEY_IS_LARGE, false)
        set(value) {
            mPrefs.edit().putBoolean(PREF_KEY_IS_LARGE, value).apply()
        }

    companion object {
        private const val PREF_KEY_RECIPE_NAME = "recipe_name"
        private val PREF_KEY_IS_LARGE = "isLargeScreen"
    }
}
