package com.khinthirisoe.bakingapp.ui.baking.view

import android.view.View
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.list_baking.view.*

class BakingViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun configureUI(baking: Recipe, position: Int) {

        itemView.txt_no_of_serving.text = baking.servings.toString()
        itemView.txt_baking_name.text = baking.name

        itemView.img_baking.setImageDrawable(itemView.context.resources.getDrawable(getImages(position)))

    }

    fun getImages(position: Int): Int {

        val bakingImages =
            arrayOf(R.drawable.nutella_pie, R.drawable.brownies, R.drawable.yellow_cake, R.drawable.cheese_cake)

        return bakingImages[position]
    }
}

