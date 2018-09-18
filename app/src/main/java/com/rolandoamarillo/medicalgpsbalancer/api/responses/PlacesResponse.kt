package com.rolandoamarillo.medicalgpsbalancer.api.responses

import com.google.gson.annotations.SerializedName
import com.rolandoamarillo.medicalgpsbalancer.model.Place

class PlacesResponse(@SerializedName("results") val results: List<Place>)