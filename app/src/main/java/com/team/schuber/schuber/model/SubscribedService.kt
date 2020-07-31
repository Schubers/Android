package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class SubscribedService(
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("theme_color")
    val themeColor: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency_type")
    val currencyType: String,
    @SerializedName("subscribers")
    val subscribers: Int,
    @SerializedName("next_purchase_at")
    val nextPurchaseAt: String,
    @SerializedName("duration_month")
    val durationMonth: Int
)