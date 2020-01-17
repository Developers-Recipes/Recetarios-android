package com.developers_recipes.recetario.models

import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.presenters.ApiPresenter
import com.developers_recipes.recetario.services.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiVersion(private val apiPresenter: ApiPresenter): ApiContract.Model{

    private var name: String = ""

    override fun setName(name: String) {
        this.name = name
        apiPresenter.showApiVersion(this.name)
    }

    override fun getName(): String {
        return name
    }


    fun getApiVersion() {
        val service = ApiClient.getInstance()
        service?.getVersionName()?.enqueue(object : Callback<ApiResponse<ApiVersion>> {
            override fun onFailure(call: Call<ApiResponse<ApiVersion>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<ApiVersion>>,
                response: Response<ApiResponse<ApiVersion>>
            ) {
                val body = response.body()
                body?.result?.getName()?.let {
                    apiPresenter.setApiVersion(it)
                }
            }
        })
    }

}
