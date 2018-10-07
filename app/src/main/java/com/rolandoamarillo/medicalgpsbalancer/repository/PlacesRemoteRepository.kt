package com.rolandoamarillo.medicalgpsbalancer.repository

import android.location.Location
import com.rolandoamarillo.medicalgpsbalancer.api.PlacesApi
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import io.reactivex.Observable
import java.lang.StringBuilder

class PlacesRemoteRepository(private val placesApi: PlacesApi, private val key: String) : PlacesDataSource {

    companion object {
        const val JSON_RESPONSE_FORMAT = "json"
        const val MEDICAL_CENTER_INPUT = "Centro Medico"
    }

    override fun getMedicalCenters(calculationParam: CalculationParam): Observable<List<Place>> {
        val location = getLocation(calculationParam)
        return placesApi.getPlacesByRadiusOnPoint(JSON_RESPONSE_FORMAT, MEDICAL_CENTER_INPUT, location, calculationParam.radius
                , key)
                .map {
                    val response = it.results
                    response.forEach {
                        it.distanceToPoint =
                                distanceBetweenPoints(it.geometry.location.lat, it.geometry.location.lng, calculationParam.lat, calculationParam.lng)
                    }
                    response
                }
    }

    private fun getLocation(calculationParam: CalculationParam): String {
        val builder = StringBuilder()
        builder.append(calculationParam.lat)
        builder.append(",")
        builder.append(calculationParam.lng)
        return builder.toString()
    }

    private fun distanceBetweenPoints(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val results = FloatArray(3)
        Location.distanceBetween(lat1, lng1, lat2, lng2, results)
        return results[0]
    }
}