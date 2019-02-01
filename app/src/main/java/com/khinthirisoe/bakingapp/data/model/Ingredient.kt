package com.khinthirisoe.bakingapp.data.model

import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("quantity") val quantity: String,
    @SerializedName("measure") val measure: String,
    @SerializedName("ingredient") val ingredient: String
)

