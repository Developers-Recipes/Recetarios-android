package com.developers_recipes.recetario.presenters

import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.models.ApiVersion

class ApiPresenter(private val view: ApiContract.View) : ApiContract.Presenter {

    private val model: ApiContract.Model = ApiVersion(this)

    override fun setApiVersion(version: String) {
        model.setName(version)
    }

    override fun showApiVersion(version: String) {
        view.showApiVersion(version)
    }
}