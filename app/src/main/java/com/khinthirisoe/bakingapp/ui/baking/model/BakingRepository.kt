package com.khinthirisoe.bakingapp.ui.baking.model

import com.khinthirisoe.bakingapp.data.model.BakingRecipe
import com.khinthirisoe.bakingapp.data.network.BakingApiService
import com.khinthirisoe.bakingapp.ui.baking.presenter.BakingPresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BakingRepository @Inject

constructor(private val bakingApiService: BakingApiService) {

    fun fetchRecipes(listener: BakingPresenter.OnFetchRecipeListener) {

        bakingApiService.getBakingRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<BakingRecipe>> {
                override fun onSuccess(t: ArrayList<BakingRecipe>) {
                    listener.onFetchRecipeSuccess(t)
                }

                override fun onSubscribe(d: Disposable) {
                    listener.onFetchRecipeDisposable(d)
                }

                override fun onError(e: Throwable) {
                    listener.onFetchRecipeFailed(e.message!!)
                }

            })
    }

}