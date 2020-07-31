package com.team.schuber.schuber.network

import com.team.schuber.schuber.model.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("/users")
    fun register(@Body body: RegisterRequestModel): Call<Unit>

    @POST("/users/auth/verification/sms")
    fun sendVerification(@Body body: VerifyRequestModel): Call<VerifyResponseModel>

    @PATCH("/users/auth/verification/sms/{verificationId}")
    fun checkVerification(@Path("verificationId") verificationId: Int, @Body body: VerifyCheckRequestModel): Call<VerifyCheckResponseModel>

    @POST("/users/auth")
    fun login(@Body body: LoginRequestModel): Call<LoginResponseModel>

}
