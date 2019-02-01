package com.khinthirisoe.bakingapp.ui.receipes.presenter

import com.khinthirisoe.bakingapp.data.model.ReceipeResponse
import com.khinthirisoe.bakingapp.ui.receipes.ReceipesContract
import com.khinthirisoe.bakingapp.ui.receipes.model.ReceipesInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ReceipesPresenter @Inject
constructor(var interactor: ReceipesInteractor) : ReceipesContract.Presenter {

    var view: ReceipesContract.View? = null
    var disposable: Disposable? = null

    override fun fetchReceipes() {
        if (view != null) view?.showProgress()
        interactor.fetchReceipes(
            object : OnFetchReceipeListener {
                override fun onFetchReceipeSuccess(receipeResponse: ReceipeResponse) {
                    if (view != null) {
                        view?.showReceipes(receipeResponse)
                        view?.hideProgress()
                    }
                }

                override fun onFetchReceipeFailed(message: String) {
                    if (view != null) {
                        view?.showMessage(message)
                        view?.hideProgress()
                    }
                }

                override fun onFetchReceipeDisposable(d: Disposable) {
                    disposable = d
                }

            })
    }

    override fun onAttachView(view: ReceipesContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        disposable?.dispose()
        this.view = null
    }

    interface OnFetchReceipeListener {

        fun onFetchReceipeSuccess(receipeResponse: ReceipeResponse)

        fun onFetchReceipeFailed(message: String)

        fun onFetchReceipeDisposable(d: Disposable)
    }

}