package com.rolandoamarillo.medicalgpsbalancer.activities.main

import dagger.Module
import android.app.Activity
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds


@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class MainActivityModuleInjector {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>

}