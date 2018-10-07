package com.rolandoamarillo.medicalgpsbalancer.activities.calculation

import dagger.Module
import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds


@Module(subcomponents = [CalculationActivitySubcomponent::class])
abstract class CalculationActivityModuleInjector {

    @Binds
    @IntoMap
    @ActivityKey(CalculationActivity::class)
    internal abstract fun bindCalculationActivityInjectorFactory(builder: CalculationActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

}