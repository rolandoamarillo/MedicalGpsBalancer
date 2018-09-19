package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rolandoamarillo.medicalgpsbalancer.R
import dagger.android.DispatchingAndroidInjector

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CalculationActivity : AppCompatActivity() {

    companion object {

        private const val LAT_PARAM = "latParam"
        private const val LNG_PARAM = "lngParam"
        private const val RADIUS_PARAM = "radiusParam"

        fun newIntent(context: Context, lat: Double, lng: Double, radius: Double): Intent {
            val newIntent = Intent(context, CalculationActivity::class.java)
            newIntent.putExtra(LAT_PARAM, lat)
            newIntent.putExtra(LNG_PARAM, lng)
            newIntent.putExtra(RADIUS_PARAM, radius)
            return newIntent
        }
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: CalculationContract.CalculationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)
        setSupportActionBar(toolbar)
    }

}
