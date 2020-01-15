package com.developers_recipes.recetario.services.api

import com.developers_recipes.recetario.models.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("get_version_name.php")
    fun getVersionName():Call<ApiResponse<ApiVersion>>

    @POST("auth/login")
    fun auth(@Body data: AuthModel):Call<ApiResponse<TokenModel>>
}