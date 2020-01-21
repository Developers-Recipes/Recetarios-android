package com.developers_recipes.recetario.services.api

import com.developers_recipes.recetario.utils.*
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient{

    private var instace: ApiService? = null

    fun getInstance(): ApiService? {
        if(instace == null){
            instace = Retrofit.Builder().run {
                val gson = GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting()
                    .create()
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create(gson))
                client(createRequestInterceptorClient())
                build()
            }.create(ApiService::class.java)
        }

        return instace
    }

    private fun createRequestInterceptorClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return if (DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(TokenInterceptor())
                .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), REQUEST_TIMEOUT_UNIT)
                .build()
        }
    }
}