package com.example.simplenotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _darkTheme = MutableLiveData<Boolean>(false)
    val darkTheme = _darkTheme

    fun setDarkTheme(isDark: Boolean) {
        _darkTheme.value = isDark
    }
}