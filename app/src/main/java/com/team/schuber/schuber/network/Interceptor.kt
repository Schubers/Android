package com.team.schuber.schuber.network

import com.team.schuber.schuber.SharedPrefStorage
import okhttp3.Interceptor
import okhttp3.Response

class Interceptor(val sharedPrefStorage: SharedPrefStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent = "okhttp/3.12.0"

        val originalRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", sharedPrefStorage.getToken(true))
            .build()

        val originalResponse = chain.proceed(originalRequest)

        return originalResponse
    }
}
