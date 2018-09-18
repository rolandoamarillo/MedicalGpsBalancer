package com.rolandoamarillo.medicalgpsbalancer

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent {

    fun inject(medicalGpsBalancerApplication: MedicalGpsBalancerApplication)

}