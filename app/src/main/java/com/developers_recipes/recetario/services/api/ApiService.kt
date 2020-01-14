package com.developers_recipes.recetario.services.api

import com.developers_recipes.recetario.models.ApiVersion
import com.developers_recipes.recetario.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("get_version_name.php")
    fun getVersionName():Call<ApiResponse<ApiVersion>>
}