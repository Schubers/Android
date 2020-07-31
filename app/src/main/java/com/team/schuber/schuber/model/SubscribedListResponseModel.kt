package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class SubscribedListResponseModel(
    @SerializedName("future_payment_total")
    val futurePaymentTotal: String,
    @SerializedName("results")
    val results: ArrayList<SubscribedService>
)