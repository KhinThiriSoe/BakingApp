package com.khinthirisoe.bakingapp.ui.baking.presenter

import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.baking.BakingContract
import com.khinthirisoe.bakingapp.ui.baking.model.BakingRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class BakingPresenter @Inject

constructor(var repository: BakingRepository) : BakingContract.Presenter {

    var view: BakingContract.View? = null
    var disposable: Disposable? = null

    override fun fetchBakingRecipes() {
        if (view != null) view?.showProgress()
        repository.fetchRecipes(
            object : OnFetchRecipeListener {
                override fun onFetchRecipeSuccess(recipe: ArrayList<Recipe>) {
                    if (view != null) {
                        view?.showBakingLists(recipe)
                        view?.hideProgress()
                    }
                }

                override fun onFetchRecipeFailed(message: String) {
                    if (view != null) {
                        view?.showMessage(message)
                        view?.hideProgress()
                    }
                }

                override fun onFetchRecipeDisposable(d: Disposable) {
                    disposable = d
                }

            })
    }

    override fun onAttachView(view: BakingContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable?.dispose()
        this.view = null
    }

    interface OnFetchRecipeListener {

        fun onFetchRecipeSuccess(recipe: ArrayList<Recipe>)

        fun onFetchRecipeFailed(message: String)

        fun onFetchRecipeDisposable(d: Disposable)
    }

}