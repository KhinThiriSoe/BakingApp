package com.khinthirisoe.bakingapp.ui.ingredients

import android.view.View
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.base.BaseViewHolder
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity
import kotlinx.android.synthetic.main.fragment_step_video.view.*


class StepsViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun onBind(step: Step, steps: ArrayList<Step>, position: Int) {

        val shortDescriptionString = "${position + 1}. ${step.shortDescription}"
        itemView.txt_short_description.text = shortDescriptionString

        itemView.setOnClickListener {
            itemView.context.startActivity(StepsActivity.createIntent(itemView.context, step, steps))
        }
    }

}