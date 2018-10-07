package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import com.rolandoamarillo.medicalgpsbalancer.BasePresenter
import com.rolandoamarillo.medicalgpsbalancer.BaseView
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place

interface CalculationContract {

    interface CalculationView : BaseView<CalculationPresenter> {

        fun calculate(calculationParam: CalculationParam)

        fun placeSelected(place: Place)

    }

    interface CalculationPresenter : BasePresenter<CalculationView> {

        fun init(calculationParam: CalculationParam)

        fun placeSelected(place: Place)
    }
}