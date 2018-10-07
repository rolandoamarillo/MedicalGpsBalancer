package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate.CalculateFragmentModuleInjector
import com.rolandoamarillo.medicalgpsbalancer.di.ActivityScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScoped
@Subcomponent(modules = [CalculationModule::class, CalculateFragmentModuleInjector::class])
interface CalculationActivitySubcomponent : AndroidInjector<CalculationActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CalculationActivity>()

}