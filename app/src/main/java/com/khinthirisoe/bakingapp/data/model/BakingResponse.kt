package com.khinthirisoe.bakingapp.data.model

import com.google.gson.annotations.SerializedName

data class BakingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("steps")
    val steps: List<Step>,
    @SerializedName("servings")
    val servings: Int,
    @SerializedName("image")
    val image: String
)
