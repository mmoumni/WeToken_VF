package com.airweb.contributionwenext

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.net.CookieManager
import java.net.CookiePolicy

import okhttp3.OkHttpClient
import okhttp3.internal.JavaNetCookieJar
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private var retrofit: Retrofit? = null
    private var cookieManager: CookieManager? = null
    private val BASE_URL = "http://34.69.227.84:4444/"

    /**
     * Create an instance of Retrofit object
     */
    val retrofitInstance: Retrofit
        get() {
            val gson = GsonBuilder()
                .setLenient().disableHtmlEscaping()
                .create()
            cookieManager = CookieManager()
            cookieManager!!.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val oktHttpClient = OkHttpClient.Builder()

                .cookieJar(JavaNetCookieJar(cookieManager))

            oktHttpClient.addInterceptor(logging)

            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(oktHttpClient.build())

                    .build()
            }
            return retrofit!!
        }

    fun clearCookies() {
        cookieManager!!.cookieStore.removeAll()
    }
}
