package com.khinthirisoe.bakingapp.data.model

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("id") val id: Int,
    @SerializedName("shortDescription") val shortDescription: String,
    @SerializedName("description") val description: String,
    @SerializedName("videoURL") val videoURL: String,
    @SerializedName("thumbnailURL") val thumbnailURL: String
)