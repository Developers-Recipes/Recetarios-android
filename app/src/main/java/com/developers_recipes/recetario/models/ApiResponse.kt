package com.developers_recipes.recetario.models

class ApiResponse<T>(val classResponse: T) {
    var code: Int = 0
    var message: String = ""
    var result: T? = null
}