package com.rolandoamarillo.medicalgpsbalancer.model

import com.google.gson.annotations.SerializedName

class Location(@SerializedName("lat") val lat: Double, @SerializedName("lng") val lng: Double)