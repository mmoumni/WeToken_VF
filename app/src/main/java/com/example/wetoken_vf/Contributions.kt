package com.example.wetoken_vf

import com.google.gson.annotations.SerializedName

class Contributions(
    @field:SerializedName("CTR_ID")
    var ctR_ID: String?, @field:SerializedName("CTR_MOTIF")
    var ctR_MOTIF: String?, @field:SerializedName("CTR_FROM")
    var ctR_FROM: String?, @field:SerializedName("CTR_TO")
    var ctR_TO: String?, @field:SerializedName("CTR_AMOUNT")
    var ctR_AMOUNT: String?, @field:SerializedName("CTR_PLAGE")
    var ctR_PLAGE: String?, @field:SerializedName("CTR_DATE")
    var ctR_DATE: String?
)
