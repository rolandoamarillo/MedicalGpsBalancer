package com.rolandoamarillo.medicalgpsbalancer.api

import com.rolandoamarillo.medicalgpsbalancer.api.responses.PlacesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApi {

    //https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=medic&inputtype=textquery&locationbias=circle:2000@47.6918452,-122.2226413&key=AIzaSyAwI9uyvL6ErLsU9pbpHSloZU2kMQSuFqk

    @GET("place/findplacefromtext/{format}")
    fun getPlacesByRadiusOnPoint(@Path("format") format: String, @Query("input") input: String,
                                 @Query("locationbias") locationbias: String, @Query("key") key: String): Observable<PlacesResponse>

}