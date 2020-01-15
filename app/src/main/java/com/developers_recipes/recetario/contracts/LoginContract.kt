package com.developers_recipes.recetario.contracts

import com.developers_recipes.recetario.models.TokenModel

interface LoginContract {
    interface Model{
        fun verifyEmail(email: String)
        fun saveUserData(token: TokenModel)
        fun authUser()
        fun updateEmail(email: String)
        fun updatePassword(password: String)
        fun validateData() : Boolean
    }

    interface View {
        fun showErrorEmail(error: String)
        fun showIncorrectCredentials()
        fun redirectToMain()
        fun disableOrEnabledButton(disable: Boolean = true)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun showErrorEmail(error: String)
        fun showIncorrectCredentials()
        fun verifyEmail(email: String)
        fun saveUserData()
        fun authUser(isValid: Boolean)
        fun loginAction()
        fun setEmail(email: String)
        fun setPassword(password: String)
        fun disableOrEnabledButton(enabled: Boolean = false)
        fun showProgress()
        fun hideProgress()
    }
}