package com.example.ourfarm.retrofitutil

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers

class ApiClient {
    companion object{

        val BASE_URL:String = "http://192.168.0.113/apiourfarm/users/"
        lateinit var retrofit: Retrofit
        fun getApiClient():Retrofit?{
            if (!this::retrofit.isInitialized){
                val gson:Gson = GsonBuilder().setLenient().create()
                retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
            }
            return retrofit
        }
    }

}