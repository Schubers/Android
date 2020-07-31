package com.team.schuber.schuber.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("access_token")
    val accessToken: String
)