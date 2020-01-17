package com.developers_recipes.recetario.contracts

interface ApiContract {

    interface View{
        fun showApiVersion(version: String)
    }

    interface Model{
        fun getName():String
        fun setName(name: String)
    }

    interface Presenter{
        fun showApiVersion(version: String)
        fun setApiVersion(version: String)
        fun fetchApiVersion()
    }
}