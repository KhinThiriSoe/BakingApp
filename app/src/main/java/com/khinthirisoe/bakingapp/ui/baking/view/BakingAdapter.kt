package com.khinthirisoe.bakingapp.ui.baking.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.base.inflate


class BakingAdapter(
    private var recipes: MutableList<Recipe>?
) : RecyclerView.Adapter<BakingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakingViewHolder {
        return BakingViewHolder(parent.inflate(R.layout.list_baking))
    }

    override fun onBindViewHolder(holder: BakingViewHolder, position: Int) {

        holder.onBind(recipes!![position], position)
    }

    override fun getItemCount(): Int {

        return if (recipes != null) {
            recipes!!.size
        } else {
            0
        }
    }

    fun setRecipes(recipes: ArrayList<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }
}