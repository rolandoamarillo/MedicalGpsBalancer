package com.rolandoamarillo.medicalgpsbalancer.repository

import com.rolandoamarillo.medicalgpsbalancer.api.PlacesApi
import com.rolandoamarillo.medicalgpsbalancer.model.Location
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import io.reactivex.Observable
import java.lang.StringBuilder

class PlacesRemoteRepository(private val placesApi: PlacesApi, private val key: String) : PlacesDataSource {

    companion object {
        const val JSON_RESPONSE_FORMAT = "json"
        const val MEDICAL_CENTER_INPUT = "Centro Medico"
        const val TEXT_INPUT_TYPE = "textquery"
        const val FIGURE_LOCATION_BIAS = "circle"
    }

    override fun getMedicalCenters(location: Location, radius: Double): Observable<List<Place>> {
        val locationBias = getLocationBias(location, radius)
        return placesApi.getPlacesByRadiusOnPoint(JSON_RESPONSE_FORMAT, MEDICAL_CENTER_INPUT, TEXT_INPUT_TYPE, locationBias, key)
                .map { it.results }
    }

    private fun getLocationBias(location: Location, radius: Double): String {
        val builder = StringBuilder()
        builder.append(FIGURE_LOCATION_BIAS)
        builder.append(":")
        builder.append(radius)
        builder.append("@")
        builder.append(location.lat)
        builder.append(location.lng)
        return builder.toString()
    }

}