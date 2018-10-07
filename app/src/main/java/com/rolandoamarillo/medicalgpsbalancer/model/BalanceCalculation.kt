package com.rolandoamarillo.medicalgpsbalancer.model

import android.os.Parcel
import android.os.Parcelable

class BalanceCalculation(val calculationParam: CalculationParam, val places: List<Place>, val result: Place?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(CalculationParam::class.java.classLoader),
            parcel.createTypedArrayList(Place),
            parcel.readParcelable(Place::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(calculationParam, flags)
        parcel.writeTypedList(places)
        parcel.writeParcelable(result, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BalanceCalculation> {
        override fun createFromParcel(parcel: Parcel): BalanceCalculation {
            return BalanceCalculation(parcel)
        }

        override fun newArray(size: Int): Array<BalanceCalculation?> {
            return arrayOfNulls(size)
        }
    }

}