package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName


data class MonthReportModel(
    @SerializedName("this_month_payment")
    val thisMonthPayment: String,
    @SerializedName("top_category")
    val topCategory: Category,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("payment_graph")
    val paymentGraph: ArrayList<GraphData>,
    @SerializedName("payment_table")
    val paymentTable: PaymentTable
)

data class Category(
    @SerializedName("category")
    val category: String,
    @SerializedName("amount")
    val amount: String
)

data class GraphData(
    @SerializedName("year_month")
    val month: String,
    @SerializedName("amount")
    val amount: Float
)

data class PaymentTable(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("character_keyword")
    val characterKeyword: String,
    @SerializedName("top_payments")
    val topPayments: ArrayList<Payment>
)

data class Payment(
    @SerializedName("percent")
    val percent: Float,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("service_name")
    val serviceName: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("amount")
    val amount: Int
)

