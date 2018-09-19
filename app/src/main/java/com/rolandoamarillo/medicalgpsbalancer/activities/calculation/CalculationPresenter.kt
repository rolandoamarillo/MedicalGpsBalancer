package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

class CalculationPresenter : CalculationContract.CalculationPresenter {

    private var view: CalculationContract.CalculationView? = null

    override fun subscribe(view: CalculationContract.CalculationView) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }
}