package com.rolandoamarillo.medicalgpsbalancer.repository

import com.rolandoamarillo.medicalgpsbalancer.model.Location
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import io.reactivex.Observable

interface PlacesDataSource {

    fun getMedicalCenters(location: Location, radius: Double): Observable<List<Place>>

}