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
    private var steps: MutableList<Step>?,
    private val clickListener: StepsAdapter.StepRecyclerViewClickListener) : RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.list_steps))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {

        return if (steps != null && steps!!.size > 0) {
            steps!!.size
        } else {
            0
        }
    }

    fun setSteps(steps: MutableList<Step>) {

        this.steps = steps
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {
            super.onBind(position)

            val step = steps!![position]

            count += 1
            val shortDescriptionString = count.toString() + ". " + step.shortDescription
            itemView.txt_short_description.text = shortDescriptionString

            itemView.setOnClickListener {
                clickListener.listItemClick(step)
            }
        }
    }

    interface StepRecyclerViewClickListener {
        fun listItemClick(step: Step)
    }

}