package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("password")
    val password: String,
    @SerializedName("verification_id")
    val verificationId: Int
)