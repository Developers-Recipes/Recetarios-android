package com.developers_recipes.recetario.models

import com.google.gson.annotations.SerializedName

class TokenModel {
    @SerializedName("access_token")
    var token: String = ""

    @SerializedName("token_type")
    var type: String = ""

    var expires_at: String =  ""
}