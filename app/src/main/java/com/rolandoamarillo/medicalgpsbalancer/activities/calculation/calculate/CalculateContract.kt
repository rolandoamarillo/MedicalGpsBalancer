package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import com.rolandoamarillo.medicalgpsbalancer.BasePresenter
import com.rolandoamarillo.medicalgpsbalancer.BaseView
import com.rolandoamarillo.medicalgpsbalancer.model.BalanceCalculation
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place

interface CalculateContract {

    interface CalculateView : BaseView<CalculatePresenter> {

        fun showMedicalCentersProgress()

        fun hideProgress()

        fun onMedicalCenters(calculationParam: CalculationParam, places: List<Place>)

        fun onMedicalCentersNotFound()

        fun onMedicalCentersError()

        fun onBalanceCalculation(balanceCalculation: BalanceCalculation)

        fun onBalanceCalculationError()

    }

    interface CalculatePresenter : BasePresenter<CalculateView> {

        fun getMedicalCenters(calculationParam: CalculationParam)

        fun balanceMedicalCenters(calculationParam: CalculationParam, places: List<Place>)

        fun placeSelected(place: Place)
    }

}