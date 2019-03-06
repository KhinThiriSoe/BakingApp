package com.khinthirisoe.bakingapp.ui.baking.view

import android.view.View
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.ingredients.IngredientsActivity
import kotlinx.android.synthetic.main.list_baking.view.*

class BakingViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun onBind(recipe: Recipe, position: Int) {

        itemView.txt_no_of_serving.text = recipe.servings.toString()
        itemView.txt_baking_name.text = recipe.name

        itemView.img_baking.setImageDrawable(itemView.context.resources.getDrawable(bakingImages[position]))

        itemView.setOnClickListener {
            itemView.context.startActivity(
                IngredientsActivity.createIntent(
                    itemView.context,
                    recipe
                )
            )
        }
    }

    companion object {

        val bakingImages =
            arrayOf(R.drawable.nutella_pie, R.drawable.brownies, R.drawable.yellow_cake, R.drawable.cheese_cake)
    }
}

