package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.base.inflate
import kotlinx.android.synthetic.main.list_steps.view.*

class StepsAdapter(
    private val stepList: MutableList<Step>,
    private val clickListener: StepsAdapter.StepRecyclerViewClickListener
) : RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_steps))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return if (stepList.isNotEmpty() && stepList.size > 0) {
            stepList.size
        } else {
            0
        }
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private val shortDescriptionTextView = itemView.txt_short_description

        override fun onBind(position: Int) {
            super.onBind(position)

            val list = stepList[position]
            if (count == 0) {
                shortDescriptionTextView.text = list.shortDescription
            } else {
                val shortDescriptionString = count.toString() + ". " + list.shortDescription
                shortDescriptionTextView.text = shortDescriptionString
            }
            count += 1

            itemView.setOnClickListener {
                clickListener.listItemClick(list)
            }

        }
    }

    interface StepRecyclerViewClickListener {
        fun listItemClick(step: Step)
    }

}