package com.rolandoamarillo.medicalgpsbalancer.activities.main

import com.rolandoamarillo.medicalgpsbalancer.di.ActivityScoped
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScoped
@Subcomponent(modules = [MainModule::class])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()


}