package com.example.ourfarm.Model

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.ourfarm.retrofitutil.ApiClient
import com.example.ourfarm.retrofitutil.IApi
import com.example.ourfarm.retrofitutil.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModel (override val email: String,override val password: String): IUserModel {

    override fun validEmail(): Int {
        if(TextUtils.isEmpty(email)){
            return 0
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return 1
        }
        else return 2
    }

    override fun validPassword(): Int{
        if(TextUtils.isEmpty(password)){
            return 0
        }
        else{
            val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%~*?&])[A-Za-z\\d@\$!%~*?&]{8,16}\$"
            val passwordMatcher = Regex(passwordPattern)
            if(passwordMatcher.find(password) == null){
                return 1
            }
            else return 2
        }
    }
}