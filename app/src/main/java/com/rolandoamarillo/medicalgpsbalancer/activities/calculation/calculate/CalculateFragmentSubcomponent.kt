package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import com.rolandoamarillo.medicalgpsbalancer.di.FragmentScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScoped
@Subcomponent(modules = [CalculateModule::class])
interface CalculateFragmentSubcomponent : AndroidInjector<CalculateFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CalculateFragment>()

}