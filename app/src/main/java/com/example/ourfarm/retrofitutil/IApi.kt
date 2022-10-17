package com.example.ourfarm.retrofitutil

import com.example.ourfarm.Model.APIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApi {
    @FormUrlEncoded
    @POST("users")
    fun performUserLogin(@Field("username")username: String, @Field("password")password: String):Call<APIResponse>
}