package com.khinthirisoe.bakingapp.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var currentPosition: Int = 0

    open fun onBind(position: Int) {
        currentPosition = position
    }
}
