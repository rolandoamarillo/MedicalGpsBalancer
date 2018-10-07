package com.rolandoamarillo.medicalgpsbalancer.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Place(@SerializedName("id") val id: String, @SerializedName("name") val name: String,
            @SerializedName("geometry") val geometry: Geometry) : Parcelable {

    var distanceToPoint: Float = Float.MAX_VALUE

    lateinit var disbalancedDistances: FloatArray

    var sumDisbalancedDistances = Double.MAX_VALUE

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Geometry::class.java.classLoader)) {
        distanceToPoint = parcel.readFloat()
        disbalancedDistances = parcel.createFloatArray()
        sumDisbalancedDistances = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeParcelable(geometry, flags)
        parcel.writeFloat(distanceToPoint)
        parcel.writeFloatArray(disbalancedDistances)
        parcel.writeDouble(sumDisbalancedDistances)
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