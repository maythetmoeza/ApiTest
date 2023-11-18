package com.example.retrofittesttwo.data

import com.google.gson.annotations.SerializedName

data class
Date(
    @SerializedName("maximum")
    val maximun : String?,

    @SerializedName("minimun")
    val minimum : String?
)
