package com.rolandoamarillo.medicalgpsbalancer

import com.rolandoamarillo.medicalgpsbalancer.activities.calculation.CalculationActivityModuleInjector
import com.rolandoamarillo.medicalgpsbalancer.activities.main.MainActivityModuleInjector
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule



@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class,
    MainActivityModuleInjector::class, CalculationActivityModuleInjector::class])
interface ApplicationComponent {

    fun inject(medicalGpsBalancerApplication: MedicalGpsBalancerApplication)

}