package com.rolandoamarillo.medicalgpsbalancer.activities.main

import com.rolandoamarillo.medicalgpsbalancer.di.ActivityScoped
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @ActivityScoped
    @Provides
    fun provideMainPresenter(): MainContract.MainPresenter {
        return MainPresenter()
    }

}