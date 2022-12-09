package com.example.water_drinking_whale.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil(context: Context) {

    private val sp: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun getSharedPreferences(key: String, defValue: String? = null): String? =
        sp.getString(key, defValue)

    fun setSharedPreferences(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun hasSharedPreferencesValue(key: String): Boolean =
        !getSharedPreferences(key).isNullOrEmpty()
}
