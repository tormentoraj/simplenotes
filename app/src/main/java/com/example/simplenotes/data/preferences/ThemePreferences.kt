package com.example.simplenotes.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesKeys
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object ThemePreferences {
    private const val PREF_NAME = "theme_preferences"
    val KEY_IS_DARK_MODE = PreferencesKeys.boolean("is_dark_mode")

    private var dataStore: androidx.datastore.core.DataStore<Preferences>? = null

    fun getDataStore(context: Context): androidx.datastore.core.DataStore<Preferences> {
        if (dataStore == null) {
            dataStore = context.preferencesDataStore(PREF_NAME)
        }
        return dataStore!!
    }

    fun isDarkMode(context: Context): Flow<Boolean> {
        return getDataStore(context).data
            .map { it[KEY_IS_DARK_MODE] ?: false }
            .distinctUntilChanged()
    }

    suspend fun setDarkMode(context: Context, isDarkMode: Boolean) {
        getDataStore(context).edit { preferences ->
            preferences[KEY_IS_DARK_MODE] = isDarkMode
        }
    }
}