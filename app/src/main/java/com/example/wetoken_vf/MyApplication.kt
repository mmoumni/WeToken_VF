package com.example.wetoken_vf

import android.app.Application

class MyApplication : Application() {
    private var myPreferenceManager: MyPreferenceManager? = null

    val prefManager: MyPreferenceManager
        get() {
            if (myPreferenceManager == null) {
                myPreferenceManager = MyPreferenceManager(this)
            }

            return myPreferenceManager as MyPreferenceManager
        }

    override fun onCreate() {
        super.onCreate()
        myApplication = this

    }



    companion object {
        @Volatile
        private var myApplication: MyApplication? = null

        val instance: MyApplication?
            get() {
                if (myApplication == null) {
                    synchronized(MyApplication::class.java) {
                        if (myApplication == null) {
                            myApplication = MyApplication()
                        }
                    }
                }

                return myApplication
            }
    }
}
