package com.khinthirisoe.bakingapp.ui.baking

import com.khinthirisoe.bakingapp.data.model.BakingRecipe
import com.khinthirisoe.bakingapp.ui.base.BasePresenter
import com.khinthirisoe.bakingapp.ui.base.BaseView

interface BakingContract {

    interface View : BaseView {

        fun showBakingLists(bakingList: ArrayList<BakingRecipe>)

        fun showMessage(message: String)
    }

    interface Presenter : BasePresenter<BakingContract.View> {

        fun fetchBakingRecipes()

    }
}