package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.ui.base.inflate

class IngredientsAdapter(
    private var ingredients: ArrayList<Ingredient>?
) : RecyclerView.Adapter<IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(parent.inflate(R.layout.list_ingredients))
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.onBind(ingredients!![position])
    }

    override fun getItemCount(): Int {

        return if (ingredients != null) {
            ingredients!!.size
        } else {
            0
        }
    }

    fun setIngredients(ingredients: ArrayList<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }
}
