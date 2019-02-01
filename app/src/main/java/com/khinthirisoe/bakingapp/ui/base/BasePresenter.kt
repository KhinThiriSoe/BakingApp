package com.khinthirisoe.bakingapp.ui.base

import com.khinthirisoe.bakingapp.ui.BaseView

interface BasePresenter<V : BaseView> {

    fun onAttachView(view: V)

    fun onDetachView()

}