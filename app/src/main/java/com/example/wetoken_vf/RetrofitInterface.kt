package com.airweb.contributionwenext

import com.example.wetoken_vf.Arbitrage
import com.example.wetoken_vf.Contributions
import com.example.wetoken_vf.Rows
import com.example.wetoken_vf.Salarie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {


    @get:GET("api/contributions/all")
    val contributions: Call<Rows>

    @POST("api/contributions/send")
    fun createContributions(@Body contributions: Contributions): Call<Contributions>


    @POST("api/arbitrage/request/send")
    fun createarbitrage(@Body Arbitrage: Arbitrage): Call<Arbitrage>

    @get:GET("api/contributions/alloneoff")
    val getContributionsOneOff: Call<Rows>

    @POST("api/contributions/createcontoff")
    fun createContributionsOneOff(@Body contributions: Contributions): Call<Contributions>

    @POST("/api/auth/login")
    fun login(@Body salarie: Salarie): Call<ResponseBody>
}
