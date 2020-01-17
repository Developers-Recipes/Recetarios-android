package com.developers_recipes.recetario.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // TODO: get token from shared preferences
        val token: String? = null

        val newRequest = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("X-Requested-With", "XMLHttpRequest")
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}