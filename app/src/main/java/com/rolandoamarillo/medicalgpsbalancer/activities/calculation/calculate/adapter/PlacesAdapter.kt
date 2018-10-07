package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rolandoamarillo.medicalgpsbalancer.R
import com.rolandoamarillo.medicalgpsbalancer.model.Place

class PlacesAdapter(private val onPlaceSelectedListener: PlaceSelectedListener?) : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    private val places = ArrayList<Place>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlacesViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.place_row, parent, false)
        return PlacesViewHolder(view, onPlaceSelectedListener)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    fun setPlaces(places: List<Place>) {
        this.places.clear()
        this.places.addAll(places)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PlacesViewHolder?, position: Int) {
        holder?.bind(places[position])
    }

    class PlacesViewHolder(itemView: View?, private val onPlaceSelectedListener: PlaceSelectedListener?) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView = itemView!!.findViewById<TextView>(R.id.nameTextView)
        private val idTextView = itemView!!.findViewById<TextView>(R.id.idTextView)
        private val functionResultTextView = itemView!!.findViewById<TextView>(R.id.functionResultTextView)
        private val distanceToPointTextView = itemView!!.findViewById<TextView>(R.id.distanceToPointTextView)

        fun bind(place: Place) {
            nameTextView.text = place.name
            idTextView.text = place.id
            functionResultTextView.text = place.sumDisbalancedDistances.toString()
            distanceToPointTextView.text = place.distanceToPoint.toString()
            itemView.tag = place
            itemView.setOnClickListener {
                val placeTag = it.tag as Place
                onPlaceSelectedListener?.onPlaceSelected(placeTag)
            }
        }
    }

    interface PlaceSelectedListener {

        fun onPlaceSelected(place: Place)

    }

}