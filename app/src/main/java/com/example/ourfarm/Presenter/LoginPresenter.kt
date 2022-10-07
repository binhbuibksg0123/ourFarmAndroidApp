package com.example.ourfarm.Presenter

import android.content.Intent
import com.example.ourfarm.Model.APIResponse
import com.example.ourfarm.Model.UserModel
import com.example.ourfarm.View.ILoginView
import com.example.ourfarm.retrofitutil.ApiClient
import com.example.ourfarm.retrofitutil.IApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginPresenter(override val iLoginView: ILoginView):ILoginPresenter {

    override fun loginFunc(email: String, password: String) {
        val user = UserModel(email,password);
        if(user.validEmail() == 0){
            iLoginView.loginErrorFunc("Don't let email empty")
        }
        else if(user.validEmail() == 1){
            iLoginView.loginErrorFunc("Please enter email format")
        }
        else if(user.validPassword() == 0){
            iLoginView.loginErrorFunc("Don't let password empty")
        }
        else if(user.validPassword() == 1){
            iLoginView.loginErrorFunc("Please enter password format")
        }
        else{
            var result = user.callLoginApi()
            if(result != "NULL"){
                iLoginView.switchActivity(result)
            }
            else{
                iLoginView.loginErrorFunc("invalid username or password, please try again!!")
            }
        }
    }
}