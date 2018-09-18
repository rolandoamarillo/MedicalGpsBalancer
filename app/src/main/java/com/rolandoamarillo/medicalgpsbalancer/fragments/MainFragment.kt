package com.rolandoamarillo.medicalgpsbalancer.fragments

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.medicalgpsbalancer.R
import kotlinx.android.synthetic.main.fragment_main.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.location.places.Place


/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        const val PLACE_PICKER_REQUEST = 1
    }

    var place: Place? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectPointButton.setOnClickListener(this)
        calculateButton.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        place?.let {
            pointSelectedTextView.text = place.toString()
        }
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.selectPointButton -> {
                val builder = PlacePicker.IntentBuilder()
                startActivityForResult(builder.build(activity), PLACE_PICKER_REQUEST)
            }
            R.id.calculateButton -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            place = PlacePicker.getPlace(activity, data)
        }
    }
}
