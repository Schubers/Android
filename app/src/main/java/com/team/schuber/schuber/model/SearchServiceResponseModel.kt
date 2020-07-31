package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class SearchServiceResponseModel(
    @SerializedName("results")
    val results: ArrayList<Service>
)