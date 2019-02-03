package com.khinthirisoe.bakingapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class ReceipeResponse(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("ingredients")
        var ingredients: List<Ingredient>,
        @SerializedName("steps")
        var steps: List<Step>,
        @SerializedName("servings")
        var servings: Int,
        @SerializedName("image")
        var image: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        TODO("ingredients"),
        TODO("steps"),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(servings)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReceipeResponse> {
        override fun createFromParcel(parcel: Parcel): ReceipeResponse {
            return ReceipeResponse(parcel)
        }

        override fun newArray(size: Int): Array<ReceipeResponse?> {
            return arrayOfNulls(size)
        }
    }
}