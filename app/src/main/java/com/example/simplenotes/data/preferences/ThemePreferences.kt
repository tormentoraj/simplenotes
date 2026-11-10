package com.example.simplenotes.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesKeys
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.rxjava3.RxDataStore
import androidx.datastore.preferences.rxjava3.RxDataStoreBuilder
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

object ThemePreferences {
    private const val PREF_NAME = "theme_preferences"
    val KEY_IS_DARK_MODE = PreferencesKeys.boolean("is_dark_mode")

    private var dataStore: RxDataStore<Preferences>? = null

    fun getDataStore(context: Context): RxDataStore<Preferences> {
        if (dataStore == null) {
            dataStore = RxDataStoreBuilder(context, PREF_NAME).build()
        }
        return dataStore!!
    }

    fun isDarkMode(context: Context): Single<Boolean> {
        return getDataStore(context).data()
            .map { it[KEY_IS_DARK_MODE] ?: false }
            .firstOrError()
    }

    fun setDarkMode(context: Context, isDarkMode: Boolean): Completable {
        return getDataStore(context).updateDataAsync { preferences ->
            preferences[KEY_IS_DARK_MODE] = isDarkMode
        }
    }
}