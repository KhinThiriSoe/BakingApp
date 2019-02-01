package com.khinthirisoe.bakingapp.ui.receipes.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_recepies.view.*

class ReceipesAdapter(private val context: Context, private var mData: MutableList<ReceipeResponse>) :
    RecyclerView.Adapter<ReceipesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_recepies))
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


        var imageReceipe = itemView.img_receipe
        var txtServing = itemView.txt_no_of_serving
        var receipeName = itemView.txt_receipe_name

        override fun onBind(position: Int) {
            super.onBind(position)

            val list = mData[position]

            setUpUI(list)

            imageReceipe.setImageDrawable(context.resources.getDrawable(getImages(position)))


        }

        private fun setUpUI(receipe: ReceipeResponse) {
            txtServing.text = receipe.servings.toString()
            receipeName.text = receipe.name
        }

        fun getImages(position: Int): Int {

            val recepieImage =
                arrayOf(R.drawable.nutella_pie, R.drawable.brownies, R.drawable.yellow_cake, R.drawable.cheese_cake)

            return recepieImage[position]
        }
    }
}