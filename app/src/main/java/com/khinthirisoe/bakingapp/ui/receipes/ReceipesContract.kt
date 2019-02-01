package com.khinthirisoe.bakingapp.ui.receipes

import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.ui.base.BasePresenter
import com.khinthirisoe.bakingapp.ui.base.BaseView

interface ReceipesContract {

    interface View : BaseView {

        fun showReceipes(receipes: ReceipeResponse)

        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter<ReceipesContract.View> {

        fun fetchReceipes()

    }
}