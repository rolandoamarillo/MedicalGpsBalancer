package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rolandoamarillo.medicalgpsbalancer.R
import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate.adapter.PlacesAdapter
import com.rolandoamarillo.medicalgpsbalancer.model.BalanceCalculation
import com.rolandoamarillo.medicalgpsbalancer.model.CalculationParam
import com.rolandoamarillo.medicalgpsbalancer.model.Place
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_calculate.*
import javax.inject.Inject

class CalculateFragment : Fragment(), CalculateContract.CalculateView, PlacesAdapter.PlaceSelectedListener {

    companion object {

        private const val CALCULATION_PARAM = "calculationParam"

        /**
         * Returns a new instance of this fragment
         */
        fun newInstance(calculationParam: CalculationParam): CalculateFragment {
            val fragment = CalculateFragment()
            val args = Bundle()
            args.putParcelable(CALCULATION_PARAM, calculationParam)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var presenter: CalculateContract.CalculatePresenter

    private lateinit var viewAdapter: PlacesAdapter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculate, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewManager = LinearLayoutManager(view!!.context)
        viewAdapter = PlacesAdapter(this)

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        presenter.subscribe(this)

        val calculationParam = arguments.getParcelable<CalculationParam>(CALCULATION_PARAM)

        presenter.getMedicalCenters(calculationParam!!)
    }

    override fun showMedicalCentersProgress() {
        errorLayout.visibility = View.GONE
        resultLayout.visibility = View.GONE
        progressTextView.setText(R.string.progress_medical_centers)
        progressLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressLayout.visibility = View.GONE
    }

    override fun onMedicalCenters(calculationParam: CalculationParam, places: List<Place>) {
        progressTextView.setText(R.string.balancing_medical_centers)
        presenter.balanceMedicalCenters(calculationParam, places)
    }

    override fun onMedicalCentersNotFound() {
        errorTextView.setText(R.string.empty_medical_centers)
        recyclerView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        resultLayout.visibility = View.GONE
    }

    override fun onMedicalCentersError() {
        errorTextView.setText(R.string.generic_error)
        errorLayout.visibility = View.VISIBLE
        resultLayout.visibility = View.GONE
    }

    override fun onDestroyView() {
        presenter.unsubscribe()
        super.onDestroyView()
    }

    override fun onBalanceCalculationError() {
        errorTextView.setText(R.string.generic_error)
        errorLayout.visibility = View.VISIBLE
        resultLayout.visibility = View.GONE
    }

    override fun onBalanceCalculation(balanceCalculation: BalanceCalculation) {
        minimumPlaceNameTextView.text = balanceCalculation.result?.name
        minimumPlaceDistanceTextView.text = balanceCalculation.result?.distanceToPoint.toString()
        minimumPlaceFunctionTextView.text = balanceCalculation.result?.sumDisbalancedDistances.toString()
        viewAdapter.setPlaces(balanceCalculation.places)
        resultLayout.visibility = View.VISIBLE
    }

    override fun onPlaceSelected(place: Place) {
        presenter.placeSelected(place)
    }
}