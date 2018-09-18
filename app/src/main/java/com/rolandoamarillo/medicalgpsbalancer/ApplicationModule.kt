package com.rolandoamarillo.medicalgpsbalancer

import android.content.Context
import com.rolandoamarillo.medicalgpsbalancer.api.PlacesApi
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    @NonNull
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    @NonNull
    fun providePlacesApi(retrofit: Retrofit): PlacesApi {
        return retrofit.create(PlacesApi::class.java)
    }

}