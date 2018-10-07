package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.CalculationContract
import com.rolandoamarillo.medicalgpsbalancer.di.FragmentScoped
import com.rolandoamarillo.medicalgpsbalancer.repository.PlacesDataSource
import dagger.Module
import dagger.Provides

@Module
class CalculateModule {

    @FragmentScoped
    @Provides
    fun provideCalculatePresenter(calculationPresenter: CalculationContract.CalculationPresenter, placesDataSource: PlacesDataSource): CalculateContract.CalculatePresenter {
        return CalculatePresenter(calculationPresenter, placesDataSource)
    }

}