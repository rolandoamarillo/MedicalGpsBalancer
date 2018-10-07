package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place

class CalculationPresenter : CalculationContract.CalculationPresenter {

    private var view: CalculationContract.CalculationView? = null

    override fun subscribe(view: CalculationContract.CalculationView) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
    }

    override fun init(calculationParam: CalculationParam) {
        this.view!!.calculate(calculationParam)
    }

    override fun placeSelected(place: Place) {
        this.view!!.placeSelected(place)
    }
}