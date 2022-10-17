package com.example.ourfarm.Presenter

import com.example.ourfarm.MqttClientHelper
import com.example.ourfarm.View.IDashboardView

interface IDashboardPresenter {
    val iDashboardView: IDashboardView
    fun getDataDashboard()
}