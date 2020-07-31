package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class CompareUsersResponseModel(
    @SerializedName("payment_pattern")
    val paymentPattern: Float,
    @SerializedName("payments_per_categories")
    val paymentPerCategories: ArrayList<PaymentPerCategory>,
    @SerializedName("total")
    val total: Total
)

data class PaymentPerCategory(
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: Float
)

data class Total(
    @SerializedName("user")
    val user: Int,
    @SerializedName("saved_amount")
    val savedAmount: Int,
    @SerializedName("purchased_amount")
    val purChasedAmount: Int
)
