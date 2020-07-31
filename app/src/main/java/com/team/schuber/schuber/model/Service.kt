package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("id")
    val id: Int,
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
    @SerializedName("description")
    val description: String,
    @SerializedName("company")
    val company: String
)