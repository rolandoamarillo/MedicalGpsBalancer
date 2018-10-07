package com.rolandoamarillo.medicalgpsbalancer.repository

import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Location
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import io.reactivex.Observable

interface PlacesDataSource {

    fun getMedicalCenters(calculationParam: CalculationParam): Observable<List<Place>>

}