package com.rolandoamarillo.medicalgpsbalancer

interface BasePresenter<T> {

    /**
     */
    fun subscribe(view: T)

    /**
     */
    fun unsubscribe()

}