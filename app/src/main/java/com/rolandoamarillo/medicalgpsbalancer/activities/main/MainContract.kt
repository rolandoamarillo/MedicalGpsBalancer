package com.rolandoamarillo.medicalgpsbalancer.activities.main

import com.rolandoamarillo.medicalgpsbalancer.BasePresenter
import com.rolandoamarillo.medicalgpsbalancer.BaseView

interface MainContract {

    interface MainView : BaseView<MainPresenter> {
    }

    interface MainPresenter : BasePresenter<MainView> {
    }
}