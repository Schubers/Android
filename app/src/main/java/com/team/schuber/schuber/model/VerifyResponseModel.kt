package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class VerifyResponseModel(
    @SerializedName("verification_id")
    val verificationId: Int
)