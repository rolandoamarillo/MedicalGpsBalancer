package com.rolandoamarillo.medicalgpsbalancer.activities.main

import io.reactivex.disposables.CompositeDisposable

class MainPresenter() : MainContract.MainPresenter {

    private var compositeDisposable: CompositeDisposable? = null

    private var view: MainContract.MainView? = null

    override fun subscribe(view: MainContract.MainView) {
        this.view = view
        compositeDisposable = CompositeDisposable()
    }

    override fun unsubscribe() {
        this.view = null
        compositeDisposable?.let {
            if (!compositeDisposable!!.isDisposed) {
                compositeDisposable?.dispose()
            }
            compositeDisposable = null
        }
    }
}