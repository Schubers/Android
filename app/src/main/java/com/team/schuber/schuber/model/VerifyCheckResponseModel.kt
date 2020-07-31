package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class VerifyCheckResponseModel (
    @SerializedName("status")
    val status: String
)