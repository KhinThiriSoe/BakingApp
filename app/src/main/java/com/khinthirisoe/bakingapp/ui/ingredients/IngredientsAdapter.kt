package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_ingredients.view.*

class IngredientsAdapter(
    private var ingredients: MutableList<Ingredient>?) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_ingredients))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {

        return if (ingredients != null && ingredients!!.size > 0) {
            ingredients!!.size
        } else {
            0
        }
    }

    fun setIngredients(ingredients: ArrayList<Ingredient>) {

        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private val ingredientTextView = itemView.txt_ingredient

        override fun onBind(position: Int) {
            super.onBind(position)

            val ingredient = ingredients!![position]
            val ingredientString = ingredient.ingredient + " " + ingredient.quantity + " " + ingredient.measure

            ingredientTextView.text = ingredientString

        }
    }
}