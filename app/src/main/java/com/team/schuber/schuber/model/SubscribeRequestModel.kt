package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class SubscribeRequestModel(
    @SerializedName("service_id")
    val serviceId: Int
)
