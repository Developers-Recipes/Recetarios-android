package com.developers_recipes.recetario.presenters

import com.developers_recipes.recetario.contracts.SignupContract
import com.developers_recipes.recetario.models.SignupModel

class SignupPresenter(val view: SignupContract.View): SignupContract.Presenter {

    private val model: SignupContract.Model = SignupModel(this)

    override fun setName(name: String) {
        model.setName(name)
    }

    override fun setLastName(lastName: String) {
        model.setLastName(lastName)
    }

    override fun setEmail(email: String) {
        model.setEmail(email)
    }

    override fun setPassword(password: String) {
        model.setPassword(password)
    }

    override fun setPasswordConfirmation(password: String) {
        model.setPasswordConfirmation(password)
    }

    override fun showEmailError(error: String) {
        view.showEmailError(error)
    }

    override fun showPasswordError(first: Boolean, error: String) {
        view.showPasswordError(first, error)
    }

    override fun enableOrDisableButton(isEnabled: Boolean) {
        view.enableOrDisableButton(isEnabled)
    }

    override fun showProgress() {
        view.showProgress()
    }

    override fun hideProgress() {
        view.hideProgress()
    }

    override fun redirectToMain() {
        view.redirectToMain()
    }

    override fun actionSignUp() {
        model.signUp()
    }

    override fun showSuccessDialog(message: String) {
        view.showSuccessDialog(message)
    }

    override fun showErrorDialog(message: String) {
        view.showErrorDialog(message)
    }
}