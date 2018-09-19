package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import com.rolandoamarillo.medicalgpsbalancer.BasePresenter
import com.rolandoamarillo.medicalgpsbalancer.BaseView

interface CalculationContract {

    interface CalculationView : BaseView<CalculationPresenter> {
    }

    interface CalculationPresenter : BasePresenter<CalculationView> {
    }
}