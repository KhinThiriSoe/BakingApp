package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Ingredient
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_ingredients.view.*

class IngreditentsAdapter(
    private val mContext: Context,
    private val mData: MutableList<Ingredient>
) :
    RecyclerView.Adapter<IngreditentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_ingredients))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (mData.isNotEmpty() && mData.size > 0) {
            mData.size
        } else {
            0
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        val ingredientTextView = itemView.txt_ingredient

        override fun onBind(position: Int) {
            super.onBind(position)

            val list = mData[position]

            ingredientTextView.text = list.ingredient + " " + list.quantity + " " + list.measure

        }
    }
}