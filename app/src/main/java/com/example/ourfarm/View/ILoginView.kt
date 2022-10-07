package com.example.ourfarm.View

interface ILoginView {
    fun loginErrorFunc(error: String)
    fun switchActivity(name: String)
}