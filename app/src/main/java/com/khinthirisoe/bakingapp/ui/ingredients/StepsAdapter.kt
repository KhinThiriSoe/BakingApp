package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_steps.view.*

class StepsAdapter(
    private val mContext: Context,
    private val mData: MutableList<Step>
) :
    RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_steps))
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

        val shortDescriptionTextView = itemView.txt_short_description

        override fun onBind(position: Int) {
            super.onBind(position)

            val list = mData[position]

            count += 1
            shortDescriptionTextView.text = count.toString() + ". " + list.shortDescription

        }
    }
}