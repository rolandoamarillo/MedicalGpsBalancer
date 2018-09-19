package com.rolandoamarillo.medicalgpsbalancer.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Place(@SerializedName("id") val id: String, @SerializedName("name") val name: String,
            @SerializedName("geometry") val geometry: Geometry) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Geometry::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeParcelable(geometry, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }
}