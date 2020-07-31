package com.team.schuber.schuber.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.team.schuber.schuber.SharedPrefStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun getNeedApi(context: Context) =
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl("http://schuber.ap-northeast-2.elasticbeanstalk.com")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(Interceptor(SharedPrefStorage(context)))
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
        )
        .build()
        .create(NeedTokenApi::class.java)

    fun getApi() =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl("http://schuber.ap-northeast-2.elasticbeanstalk.com")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .build()
            )
            .build()
            .create(Api::class.java)
}