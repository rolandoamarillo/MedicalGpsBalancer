package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import com.rolandoamarillo.medicalgpsbalancer.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class CalculationModule {

    @ActivityScoped
    @Provides
    fun provideCalculationPresenter(): CalculationContract.CalculationPresenter {
        return CalculationPresenter()
    }

}