package com.airweb.contributionwenext

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context) {
    internal var mySharedPref: SharedPreferences

    init {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE)
    }

    // this method will save the nightMode State : True or False
    fun setNightModeState(state: Boolean?) {
        val editor = mySharedPref.edit()
        editor.putBoolean("NightMode", state!!)
        editor.commit()

    }

    // this method will load the Night Mode State
    fun loadNightModeState(): Boolean? {
        return mySharedPref.getBoolean("NightMode", false)
    }
}