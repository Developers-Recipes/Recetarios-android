package com.developers_recipes.recetario.contracts

interface SignupContract {
    interface Model {
        fun setName(name: String)
        fun setLastname(lastName: String)
        fun setEmail(email: String)
        fun setPassword(password: String)
        fun setPasswordConfimation(password: String)
        fun signUp()
        fun verifyEmail()
        fun confirmPassword()
        fun verifyData() : Boolean
    }

    interface View {
        fun showEmailError(error: String)
        fun showPasswordError(error: String)
        fun enableOrDisableButton(isEnabled: Boolean)
        fun showProgress()
        fun hideProgress()
        fun redirectToMain()
        fun showSuccessDialog(message: String)
        fun showErrorDialog(message: String)
    }

    interface Presenter {
        fun setName(name: String)
        fun setLastName(lastName: String)
        fun setEmail(email: String)
        fun setPassword(password: String)
        fun setPasswordConfirmation(password: String)
        fun showEmailError(error: String)
        fun showPasswordError(error: String)
        fun enableOrDisableButton(isEnabled: Boolean)
        fun showProgress()
        fun hideProgress()
        fun redirectToMain()
        fun actionSignUp()
        fun showSuccessDialog(message: String)
        fun showErrorDialog(message: String)
    }
}