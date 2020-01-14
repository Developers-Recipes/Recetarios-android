package com.developers_recipes.recetario.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developers_recipes.recetario.services.api.ApiClient
import com.developers_recipes.recetario.R
import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.models.ApiVersion
import com.developers_recipes.recetario.models.ApiResponse
import com.developers_recipes.recetario.presenters.ApiPresenter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ApiContract.View{
    val presenter: ApiContract.Presenter = ApiPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getApiVersion()
    }


    private fun getApiVersion(){
        val service = ApiClient.getInstance()
        service?.getVersionName()?.enqueue(object : Callback<ApiResponse<ApiVersion>> {
            override fun onFailure(call: Call<ApiResponse<ApiVersion>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse<ApiVersion>>,
                response: Response<ApiResponse<ApiVersion>>
            ){
                val body = response.body()
                body?.result?.getName()?.let { presenter.setApiVersion(it) }
            }

        })
    }

    override fun showApiVersion(version: String) {
        appVersion.text = version
    }

}
