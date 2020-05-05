package com.example.denden

import android.content.Context
import android.content.res.Configuration
import android.preference.PreferenceManager
import java.util.*


object changeLang {

    fun saveLang(lang:String,context: Context){
        val sharedPrf = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPrf?.edit()?.putString("lang",lang)?.commit()
    }

    fun getLang(context: Context):String{
        val sharedPrf = PreferenceManager.getDefaultSharedPreferences(context)
        val lang = sharedPrf?.getString("lang", "fr")
        return lang!!
    }


    fun attach(context: Context): Context {
        return switchLang(context,getLang(context))
    }

    fun attach(context: Context, lang:String): Context {
        return switchLang(context,lang)
    }





    fun switchLang(context: Context, lang: String): Context {
        var newcontext = context
        println(lang)
        var lcal = Locale(lang)
        Locale.setDefault(lcal)
        var cnf = Configuration(context.resources?.configuration)
        //var cnf = Configuration()
        cnf.setLocale(lcal)
        // activity?.baseContext?.createConfigurationContext(cnf)
        newcontext = context.createConfigurationContext(cnf)
        context.createConfigurationContext(cnf)
        return newcontext
    }
}