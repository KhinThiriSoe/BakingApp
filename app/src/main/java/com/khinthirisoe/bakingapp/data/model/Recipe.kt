package com.khinthirisoe.bakingapp.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ingredients") val ingredients: ArrayList<Ingredient>,
    @SerializedName("steps") val steps: ArrayList<Step>,
    @SerializedName("servings") val servings: Int,
    @SerializedName("image") var image: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArrayList(Ingredient.CREATOR),
        parcel.createTypedArrayList(Step.CREATOR),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(steps)
        parcel.writeInt(servings)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }

}