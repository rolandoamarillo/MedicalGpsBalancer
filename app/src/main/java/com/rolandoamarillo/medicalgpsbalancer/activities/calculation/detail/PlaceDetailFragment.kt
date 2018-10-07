package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.medicalgpsbalancer.R
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import kotlinx.android.synthetic.main.fragment_place_detail.*
import java.lang.StringBuilder

class PlaceDetailFragment : Fragment() {

    companion object {

        const val TAG = "PlaceDetail"

        private const val PLACE_PARAM = "placeParam"

        /**
         * Returns a new instance of this fragment
         */
        fun newInstance(place: Place): PlaceDetailFragment {
            val fragment = PlaceDetailFragment()
            val args = Bundle()
            args.putParcelable(PLACE_PARAM, place)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_place_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val place = arguments.getParcelable<Place>(PLACE_PARAM)

        nameTextView.text = place!!.name
        idTextView.text = place.id
        functionResultTextView.text = place.sumDisbalancedDistances.toString()
        distanceToPointTextView.text = place.distanceToPoint.toString()
        val stringBuilder = StringBuilder()

        for (i in 0 until place.disbalancedDistances.size) {
            stringBuilder.append(i.toString() + ": " + place.disbalancedDistances[i])
            stringBuilder.append("\n")
        }

        distancesTextView.text = stringBuilder
    }
}