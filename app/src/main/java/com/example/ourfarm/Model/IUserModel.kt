package com.example.ourfarm.Model

interface IUserModel {
    val email: String
    val password: String
    fun validPassword(): Int
    fun validEmail(): Int
}