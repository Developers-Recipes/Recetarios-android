package com.developers_recipes.recetario.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.developers_recipes.recetario.R
import com.developers_recipes.recetario.contracts.LoginContract
import com.developers_recipes.recetario.presenters.LoginPresenter
import com.kinda.alert.KAlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter = LoginPresenter(this)
    private lateinit var progressDialog: KAlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.isEnabled = false
        this.progressDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        setupWatchers()

        btnLogin.setOnClickListener {
            presenter.loginAction()
        }
    }

    private fun setupWatchers() {


        val pwdWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setPassword(s.toString())
            }

        })

        val emailWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.verifyEmail(email.text.toString())
                presenter.setEmail(s.toString())
            }

        })

        email.addTextChangedListener(emailWatcher)
        password.addTextChangedListener(pwdWatcher)

    }

    override fun showErrorEmail(error: String) {
        email.error = error
    }

    override fun showIncorrectCredentials() {
        val alert = KAlertDialog(this, KAlertDialog.ERROR_TYPE)
        alert.titleText = "Login"
        alert.contentText = "Invalid Credentials"
        alert.show()
    }

    override fun redirectToMain() {
        finish()
    }

    override fun disableOrEnabledButton(disable: Boolean) {
        btnLogin.isEnabled = disable
    }

    override fun showProgress() {
        progressDialog.titleText = "Please wait"
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    override fun hideProgress() {
        if(progressDialog.isShowing)
            progressDialog.dismiss()
    }
}
