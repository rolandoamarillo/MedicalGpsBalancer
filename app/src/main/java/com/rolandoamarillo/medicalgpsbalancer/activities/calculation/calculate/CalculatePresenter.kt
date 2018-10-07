package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import android.util.Log
import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.CalculationContract
import com.rolandoamarillo.medicalgpsbalancer.model.BalanceCalculation
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import com.rolandoamarillo.medicalgpsbalancer.repository.PlacesDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CalculatePresenter(private val calculationPresenter: CalculationContract.CalculationPresenter, private val placesDataSource: PlacesDataSource) : CalculateContract.CalculatePresenter {

    private var view: CalculateContract.CalculateView? = null
    private val compositeDisposable = CompositeDisposable()

    override fun subscribe(view: CalculateContract.CalculateView) {
        this.view = view
    }

    override fun unsubscribe() {
        this.view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        compositeDisposable.clear()
    }

    override fun getMedicalCenters(calculationParam: CalculationParam) {
        view?.showMedicalCentersProgress()
        val disposable = placesDataSource.getMedicalCenters(calculationParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.hideProgress()
                    if (!it.isEmpty()) {
                        view?.onMedicalCenters(calculationParam, it)
                    } else {
                        view?.onMedicalCentersNotFound()
                    }
                }, {
                    view?.hideProgress()
                    Log.e("CalculatePresenter", it.message, it)
                    view?.onMedicalCentersError()
                })
        compositeDisposable.add(disposable)
    }

    override fun balanceMedicalCenters(calculationParam: CalculationParam, places: List<Place>) {
        val disposable = Observable.just(places)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    for (i in 0 until places.size) {
                        val disbalancedDistances = FloatArray(places.size)
                        var sumDistances = 0.0
                        for (j in 0 until places.size) {
                            val distance = Math.abs(places[i].distanceToPoint - places[j].distanceToPoint)
                            disbalancedDistances[j] = distance
                            sumDistances += distance
                        }
                        places[i].disbalancedDistances = disbalancedDistances
                        places[i].sumDisbalancedDistances = sumDistances
                    }
                    it
                }
                .map {
                    var minimumDistance = Double.MAX_VALUE
                    var selectedPlace: Place? = null
                    for (place in it) {
                        if (place.sumDisbalancedDistances < minimumDistance) {
                            minimumDistance = place.sumDisbalancedDistances
                            selectedPlace = place
                        }
                    }
                    val balanceCalculation = BalanceCalculation(calculationParam, it, selectedPlace)
                    balanceCalculation
                }
                .subscribe({
                    view?.hideProgress()
                    view?.onBalanceCalculation(it)
                }, {
                    it.printStackTrace()
                    view?.hideProgress()
                    view?.onBalanceCalculationError()
                })
        compositeDisposable.add(disposable)
    }

    override fun placeSelected(place: Place) {
        calculationPresenter.placeSelected(place)
    }
}