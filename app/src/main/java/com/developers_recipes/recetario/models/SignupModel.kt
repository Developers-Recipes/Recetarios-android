package com.developers_recipes.recetario.models

import android.text.TextUtils
import android.util.Patterns
import com.developers_recipes.recetario.contracts.SignupContract
import com.developers_recipes.recetario.services.api.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupModel(private val presenter: SignupContract.Presenter) : SignupContract.Model {

    private var name: String = ""
    private var lastName: String = ""
    private var email: String = ""
    private var password: String = ""
    private var passwordConfirmation: String = ""
    private val emailError  = "Incorrect e-mail format"
    private val passwordError = "Passwords Don't Match"
    private val passwordLengthError = "Must contain at least 8 characters"

    override fun setName(name: String) {
        this.name = name
        presenter.enableOrDisableButton(verifyData())
    }

    override fun setLastName(lastName: String) {
        this.lastName = lastName
        presenter.enableOrDisableButton(verifyData())
    }

    override fun setEmail(email: String) {
        this.email = email
        presenter.enableOrDisableButton(verifyData())
        verifyEmail()
    }

    override fun setPassword(password: String) {
        this.password = password
        presenter.enableOrDisableButton(verifyData())
        verifyLengthPassword()
    }

    override fun setPasswordConfirmation(password: String) {
        this.passwordConfirmation =  password
        presenter.enableOrDisableButton(verifyData())
        confirmPassword()
    }

    override fun signUp() {

        val user = User()
        user.name = "${this.name} ${this.lastName}"
        user.email = this.email
        user.password = this.password
        user.passwordConfirmation = this.passwordConfirmation

        val call = ApiClient.getInstance()?.signup(user)

        presenter.showProgress()
        call?.enqueue(object: Callback<ApiResponse<User>>{
            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                println(t.message)
                presenter.hideProgress()
                presenter.showErrorDialog(t.message.toString())
            }

            override fun onResponse(
                call: Call<ApiResponse<User>>,
                response: Response<ApiResponse<User>>
            ) {
                presenter.hideProgress()
                if (response.code() == 201){
                    presenter.showSuccessDialog("Register Successfully")
                }else{
                    presenter.showErrorDialog("Already exists an account with this email")
                }

            }

        })

    }

    override fun verifyEmail() {
        if(!TextUtils.isEmpty(this.email))
            if(!Patterns.EMAIL_ADDRESS.matcher(this.email).matches())
                presenter.showEmailError(this.emailError)
    }

    override fun confirmPassword() {
        if(!TextUtils.isEmpty(this.passwordConfirmation))
            if (this.password != this.passwordConfirmation)
                presenter.showPasswordError(false, passwordError)
    }

    override fun verifyData(): Boolean {

        val nameData = !TextUtils.isEmpty(this.name) && !TextUtils.isEmpty(this.lastName)
        val passwordData = !TextUtils.isEmpty(this.password) && !TextUtils.isEmpty(this.passwordConfirmation) &&
                (this.password == this.passwordConfirmation) && this.password.length >= 8

        val emailData = !TextUtils.isEmpty(this.email) && Patterns.EMAIL_ADDRESS.matcher(this.email).matches()

        return nameData && passwordData && emailData
    }

    override fun verifyLengthPassword() {
        if(!TextUtils.isEmpty(this.password))
            if (this.password.length < 8)
                presenter.showPasswordError(true, passwordLengthError)
    }
}