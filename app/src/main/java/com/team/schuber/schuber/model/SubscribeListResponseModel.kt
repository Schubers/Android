package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class SubscribeListResponseModel(
    @SerializedName("results")
    val results: ArrayList<Service>
)