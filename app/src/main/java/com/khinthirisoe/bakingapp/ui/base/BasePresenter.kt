package com.khinthirisoe.bakingapp.ui.base

interface BasePresenter<V : BaseView> {

    fun onAttachView(view: V)

    fun onDetachView()

}