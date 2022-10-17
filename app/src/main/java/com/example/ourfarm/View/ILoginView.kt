package com.example.ourfarm.View

import com.example.ourfarm.Model.UserInfor

interface ILoginView {
    fun loginErrorFunc(error: String)
    fun switchActivity(user: UserInfor)
}