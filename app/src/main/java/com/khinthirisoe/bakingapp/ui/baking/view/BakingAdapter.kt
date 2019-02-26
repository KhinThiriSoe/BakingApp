package com.khinthirisoe.bakingapp.ui.baking.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_baking.view.*

class BakingAdapter(
    private val context: Context,
    private var recipes: MutableList<Recipe>?,
    private val clickListener: BakingRecyclerViewClickListener
) :
    RecyclerView.Adapter<BakingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_baking))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {

        return if (recipes != null && recipes!!.size > 0) {
            recipes!!.size
        } else {
            0
        }
    }

    fun setRecipes(recipes: ArrayList<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private var bakingImageView = itemView.img_baking
        private var servingTextView = itemView.txt_no_of_serving
        private var bakingName = itemView.txt_baking_name

        override fun onBind(position: Int) {
            super.onBind(position)

            val recipe = recipes!![position]

            setUpUI(recipe)

            bakingImageView.setImageDrawable(context.resources.getDrawable(getImages(position)))

            itemView.setOnClickListener {
                clickListener.listItemClick(recipe)
            }

        }

        private fun setUpUI(baking: Recipe) {
            servingTextView.text = baking.servings.toString()
            bakingName.text = baking.name
        }

        private fun getImages(position: Int): Int {

            val bakingImages =
                arrayOf(R.drawable.nutella_pie, R.drawable.brownies, R.drawable.yellow_cake, R.drawable.cheese_cake)

            return bakingImages[position]
        }
    }

    interface BakingRecyclerViewClickListener {
        fun listItemClick(baking: Recipe)
    }
    
}