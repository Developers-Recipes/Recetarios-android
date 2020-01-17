package com.developers_recipes.recetario.models

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.developers_recipes.recetario.contracts.LoginContract
import com.developers_recipes.recetario.services.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel(private val presenter: LoginContract.Presenter) : LoginContract.Model {

    private var email: String = ""
    private var password: String = ""
    private val errorEmail = "Incorrect e-mail format"


    override fun verifyEmail(email: String) {
        if (!TextUtils.isEmpty(email))
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                presenter.showErrorEmail(errorEmail)

    }

    override fun saveUserData(token: TokenModel) {
        //TODO: save token in shared Preferences
    }

    override fun authUser() {

        val data = AuthModel(this.email, this.password)
        val call = ApiClient.getInstance()?.auth(data)
        presenter.showProgress()

        call?.enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                presenter.hideProgress()
                presenter.authUser(false)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                presenter.hideProgress()
                if (response.code() == 200) {
                    response.body()?.let { saveUserData(it) }
                    presenter.authUser(true)
                } else {
                    presenter.authUser(false)
                }
            }

        })
    }

    override fun updateEmail(email: String) {
        this.email = email
        presenter.disableOrEnabledButton(validateData())
    }

    override fun updatePassword(password: String) {
        this.password = password
        presenter.disableOrEnabledButton(validateData())
    }

    override fun validateData(): Boolean {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && Patterns.EMAIL_ADDRESS.matcher(
            email
        ).matches()
    }
}