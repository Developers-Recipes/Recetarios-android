package com.developers_recipes.recetario.models

import com.google.gson.annotations.SerializedName

class User{
    var id: Int = -1
    var name: String = ""
    var email: String = ""
    var password: String = ""
    @SerializedName("password_confirmation")
    var passwordConfirmation: String = ""
}