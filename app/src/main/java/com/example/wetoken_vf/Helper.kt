package com.example.wetoken_vf


import java.util.ArrayList

object Helper {

    val supportedLangage: List<Language>
        get() {
            val languages = ArrayList<Language>()
            languages.add(Language(1, MyApplication.instance!!.getString(R.string.fr)))
            languages.add(Language(2, MyApplication.instance!!.getString(R.string.en)))
            return languages
        }
}
