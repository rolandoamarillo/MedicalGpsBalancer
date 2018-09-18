package com.rolandoamarillo.medicalgpsbalancer.model

import com.google.gson.annotations.SerializedName

class MedicalCenter(@SerializedName("id") val id: String, @SerializedName("name") val name: String, @SerializedName("geometry") val geometry: Geometry)