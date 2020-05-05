package com.airweb.contributionwenext

import android.content.Context
import android.content.SharedPreferences

class SharedPref1(val context: Context) {

        val mySharedPref: SharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE)


    // this method will save the nightMode State : True or False
    fun saveenglishmode(state: Boolean?) {
        val editor: SharedPreferences.Editor = mySharedPref.edit()
        editor.putBoolean("englishmode", state!!)
        editor.commit()

    }



    // this method will load the Night Mode State
    fun getenglishmode(): Boolean? {


        return mySharedPref.getBoolean("englishmode", false)
    }
}