package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.View
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.list_ingredients.view.*

class IngredientsViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun configureUI(ingredient: Ingredient) {

        val ingredientString = ingredient.ingredient + " " + ingredient.quantity + " " + ingredient.measure
        itemView.txt_ingredient.text = ingredientString
    }

}