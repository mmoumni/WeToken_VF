package com.example.wetoken_vf

import android.content.Context
import android.content.SharedPreferences

class MyPreferenceManager (private val context: Context) {
    private val TAG = MyPreferenceManager::class.java!!.getSimpleName()
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val PRIVATE_MODE = 0

    init {
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun clearPreference() {
        editor.clear()
        editor.commit()
    }

    fun loadNightModeState(): Boolean? {
        return pref.getBoolean("NightMode", false)
    }

    companion object {
        private val PREF_NAME = "com.effitek.quovadev.keplerk"
    }


}
