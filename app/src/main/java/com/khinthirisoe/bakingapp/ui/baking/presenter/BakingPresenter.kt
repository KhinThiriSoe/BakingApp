package com.khinthirisoe.bakingapp.ui.baking.presenter

import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.ui.baking.BakingContract
import com.khinthirisoe.bakingapp.ui.baking.model.BakingRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BakingPresenter @Inject

constructor(var repository: BakingRepository) : BakingContract.Presenter {

    var view: BakingContract.View? = null
    var disposable: CompositeDisposable? = null

    override fun fetchBakingRecipes() {
        if (view != null) view?.showProgress()

        repository.fetchRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<Recipe>> {
                override fun onSuccess(recipes: ArrayList<Recipe>) {
                    if (view != null) {
                        view?.showBakingLists(recipes)
                        view?.hideProgress()
                    }
                }

                override fun onSubscribe(d: Disposable) {
                    disposable?.add(d)
                }

                override fun onError(e: Throwable) {
                    if (view != null) {
                        view?.showMessage(e.message!!)
                        view?.hideProgress()
                    }
                }

            })
    }

    override fun onAttachView(view: BakingContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable?.clear()
        this.view = null
    }
}