package com.team.schuber.schuber.network

import com.team.schuber.schuber.model.*
import retrofit2.Call
import retrofit2.http.*

interface NeedTokenApi {

    @GET("/analytics/monthly")
    fun monthReport(): Call<MonthReportModel>

    @GET("/analytics/average")
    fun compareUsers(): Call<CompareUsersResponseModel>

    @GET("/services/rank/realtime")
    fun rankRealTime(): Call<SubscribeListResponseModel>

    @GET("/services/rank/popular")
    fun rankService(): Call<SubscribeListResponseModel>

    @GET("/services/recommendation")
    fun listForYou(): Call<SubscribeListResponseModel>

    @GET("/services/{serviceId}")
    fun serviceDetail(@Path("serviceId") serviceId: Int): Call<Service>

    @POST("/subscriptions")
    fun subscribe(@Body body: SubscribeRequestModel): Call<Unit>

    @GET("/subscriptions")
    fun getSubscribedList(): Call<SubscribedListResponseModel>

    @GET("/services")
    fun searchService(@Query("key") key: String): Call<SearchServiceResponseModel>

}