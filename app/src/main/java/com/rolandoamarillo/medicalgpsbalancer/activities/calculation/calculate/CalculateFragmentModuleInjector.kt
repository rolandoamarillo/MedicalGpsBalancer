package com.rolandoamarillo.medicalgpsbalancer.activities.calculation.calculate

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [CalculateFragmentSubcomponent::class])
abstract class CalculateFragmentModuleInjector {

    @Binds
    @IntoMap
    @FragmentKey(CalculateFragment::class)
    internal abstract fun bindCalculateFragmentInjectorFactory(builder: CalculateFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>


}