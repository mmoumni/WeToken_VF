package com.example.wetoken_vf

import com.google.gson.annotations.SerializedName

class Arbitrage(
    @field:SerializedName("ENT_NUM")
    var ENT_NUM: String?,
    @field:SerializedName("employe")
    var employe: String?,
    @field:SerializedName("FROM")
    var FROM: String?,
    @field:SerializedName("repartition")
    var repartition: String?,
    @field:SerializedName("EXP_DT")
    var EXP_DT: String?
)
