package com.example.ourfarm.Presenter

import com.example.ourfarm.View.ILoginView

interface ILoginPresenter {
    val iLoginView: ILoginView
    fun loginFunc(email: String,password: String)
}