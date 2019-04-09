package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.base.inflate

class StepsAdapter(
    private var steps: ArrayList<Step>?,
    private val clickListener: StepsRecyclerViewClickListener
) : RecyclerView.Adapter<StepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder(parent.inflate(R.layout.list_steps))
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {

        holder.onBind(steps!!, position)

        holder.itemView.setOnClickListener {
            clickListener.listItemClick(position)
        }
    }

    override fun getItemCount(): Int {

        return if (steps != null) {
            steps!!.size
        } else {
            0
        }
    }

    fun setSteps(steps: ArrayList<Step>) {

        this.steps = steps
        notifyDataSetChanged()
    }

    interface StepsRecyclerViewClickListener {
        fun listItemClick(position: Int)
    }
}