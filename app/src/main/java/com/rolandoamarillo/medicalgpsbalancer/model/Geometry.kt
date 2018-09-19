package com.rolandoamarillo.medicalgpsbalancer.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Geometry(@SerializedName("location") val location: Location) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readParcelable(Location::class.java.classLoader) as Location)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(location, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}