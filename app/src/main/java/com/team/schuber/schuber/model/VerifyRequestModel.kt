package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

class VerifyRequestModel(
    @SerializedName("phone")
    val phone: String
)