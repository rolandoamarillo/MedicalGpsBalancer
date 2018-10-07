package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.rolandoamarillo.medicalgpsbalancer.R
import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate.CalculateFragment
import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.detail.PlaceDetailFragment
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CalculationActivity : AppCompatActivity(), CalculationContract.CalculationView, HasSupportFragmentInjector {

    companion object {

        private const val CALCULATION_PARAM = "calculationParam"

        fun newIntent(context: Context, calculationParam: CalculationParam): Intent {
            val newIntent = Intent(context, CalculationActivity::class.java)
            newIntent.putExtra(CALCULATION_PARAM, calculationParam)
            return newIntent
        }
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: CalculationContract.CalculationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculation)
        setSupportActionBar(toolbar)

        presenter.subscribe(this)

        val calculation = intent.getParcelableExtra<CalculationParam>(CALCULATION_PARAM)

        presenter.init(calculation)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun calculate(calculationParam: CalculationParam) {
        val newFragment = CalculateFragment.newInstance(calculationParam)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment)
        transaction.commit()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun placeSelected(place: Place) {
        val newFragment = PlaceDetailFragment.newInstance(place)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, newFragment, PlaceDetailFragment.TAG)
        transaction.addToBackStack(PlaceDetailFragment.TAG)
        transaction.commit()
    }
}
