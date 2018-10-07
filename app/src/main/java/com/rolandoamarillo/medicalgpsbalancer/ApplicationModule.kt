package com.rolandoamarillo.medicalgpsbalancer

import android.content.Context
import com.rolandoamarillo.medicalgpsbalancer.api.PlacesApi
import com.rolandoamarillo.medicalgpsbalancer.repository.PlacesDataSource
import com.rolandoamarillo.medicalgpsbalancer.repository.PlacesRemoteRepository
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


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
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .client(httpClient.build())
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

    @Singleton
    @Provides
    fun providePlacesDataSource(context: Context, placesApi: PlacesApi): PlacesDataSource {
        val key = context.getString(R.string.google_api_key)
        return PlacesRemoteRepository(placesApi, key)
    }
}