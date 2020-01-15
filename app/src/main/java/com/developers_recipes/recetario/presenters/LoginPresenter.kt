package com.developers_recipes.recetario.presenters
import com.developers_recipes.recetario.contracts.LoginContract
import com.developers_recipes.recetario.models.LoginModel

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {

    private val model: LoginContract.Model = LoginModel(this)

    override fun loginAction() {
        model.authUser()
    }

    override fun setEmail(email: String) {
        model.updateEmail(email)
    }

    override fun setPassword(password: String) {
        model.updatePassword(password)
    }

    override fun disableOrEnabledButton(enabled: Boolean) {
        view.disableOrEnabledButton(enabled)
    }

    override fun showProgress() {
        view.showProgress()
    }

    override fun hideProgress() {
        view.hideProgress()
    }

    override fun authUser(isValid: Boolean) {

        if(isValid)
            view.redirectToMain()
        else
            view.showIncorrectCredentials()
    }


    override fun showErrorEmail( error: String) {
        view.showErrorEmail(error)
    }

    override fun showIncorrectCredentials() {

    }

    override fun verifyEmail(email: String) {
        model.verifyEmail(email)
    }

    override fun saveUserData() {

    }
}