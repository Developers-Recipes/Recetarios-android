package com.developers_recipes.recetario.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.models.ApiResponse
import com.developers_recipes.recetario.models.ApiVersion
import com.developers_recipes.recetario.services.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }


    val text: LiveData<String> = _text
    var apiVersion: MutableLiveData<String> = MutableLiveData()

    init {
        getApiVersion()
    }

    private fun getApiVersion() {
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
                body?.result?.getName()?.let { cadena ->
                    //presenter.setApiVersion(it)
                    apiVersion.apply { value = cadena }
                }
            }
        })
    }
}