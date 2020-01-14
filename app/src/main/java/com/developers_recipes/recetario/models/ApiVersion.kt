package com.developers_recipes.recetario.models

import com.developers_recipes.recetario.contracts.ApiContract
import com.developers_recipes.recetario.presenters.ApiPresenter

class ApiVersion(private val apiPresenter: ApiPresenter): ApiContract.Model{
    private var name: String = ""

    override fun setName(name: String) {
        this.name = name
        apiPresenter.showApiVersion(this.name)
    }

    override fun getName(): String {
        return name
    }

}
