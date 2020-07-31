package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class VerifyCheckRequestModel (
    @SerializedName("verification_code")
    val verificationCode: Int
)