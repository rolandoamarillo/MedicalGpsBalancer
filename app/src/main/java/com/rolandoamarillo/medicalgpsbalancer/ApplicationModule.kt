package com.rolandoamarillo.medicalgpsbalancer

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    @NonNull
    fun provideContext(): Context {
        return context
    }

}