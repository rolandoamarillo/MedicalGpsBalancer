package com.rolandoamarillo.medicalgpsbalancer.model

import android.os.Parcel
import android.os.Parcelable

class CalculationParam(val lat: Double, val lng: Double, val radius: Double) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
        parcel.writeDouble(radius)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalculationParam> {
        override fun createFromParcel(parcel: Parcel): CalculationParam {
            return CalculationParam(parcel)
        }

        override fun newArray(size: Int): Array<CalculationParam?> {
            return arrayOfNulls(size)
        }
    }


}