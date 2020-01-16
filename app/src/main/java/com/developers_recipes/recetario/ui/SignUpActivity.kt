package com.developers_recipes.recetario.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.developers_recipes.recetario.R
import com.developers_recipes.recetario.contracts.SignupContract
import com.developers_recipes.recetario.presenters.SignupPresenter
import com.kinda.alert.KAlertDialog
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity(), SignupContract.View {

    private val presenter: SignupContract.Presenter = SignupPresenter(this)
    private lateinit var progressDialog: KAlertDialog
    private lateinit var alert: KAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignup.isEnabled = false

        setupWatchers()

        btnSignup.setOnClickListener { presenter.actionSignUp() }
    }



    override fun showEmailError(error: String) {
        email.error = error
    }

    override fun showPasswordError(first: Boolean, error: String) {
       if(first)
           password.error = error
       else
           password_conf.error = error
    }

    override fun enableOrDisableButton(isEnabled: Boolean) {
        btnSignup.isEnabled = isEnabled
    }

    override fun showProgress() {
        this.progressDialog = KAlertDialog(this, KAlertDialog.PROGRESS_TYPE)
        this.progressDialog.titleText = "Please wait"
        this.progressDialog.setCancelable(false)
        this.progressDialog.show()
    }

    override fun hideProgress() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

    override fun redirectToMain() {
        finish()
    }

    override fun showSuccessDialog(message: String) {
        setupDialog()
        alert.changeAlertType(KAlertDialog.SUCCESS_TYPE)
        alert.contentText = message
        alert.setConfirmClickListener{
            it.dismiss()
            this.finish()
        }
        alert.show()
    }

    override fun showErrorDialog(message: String) {
        setupDialog(KAlertDialog.ERROR_TYPE)
        alert.changeAlertType(KAlertDialog.ERROR_TYPE)
        alert.contentText = message
        alert.show()
    }

    private fun setupDialog(type: Int = KAlertDialog.SUCCESS_TYPE){
        this.alert = KAlertDialog(this,type)
        this.alert.titleText = "Sign Up"
    }

    private fun setupWatchers(){
        val nameWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setName(s.toString())
            }
        })

        val lastNameWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setLastName(s.toString())
            }
        })

        val emailWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setEmail(s.toString())
            }
        })

        val passwordWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setPassword(s.toString())
            }
        })

        val confirmPasswordWatcher = (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.setPasswordConfirmation(s.toString())
            }
        })

        name.addTextChangedListener(nameWatcher)
        last_name.addTextChangedListener(lastNameWatcher)
        email.addTextChangedListener(emailWatcher)
        password.addTextChangedListener(passwordWatcher)
        password_conf.addTextChangedListener(confirmPasswordWatcher)

    }
}
