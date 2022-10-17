package com.example.ourfarm.Presenter

import android.content.Intent
import android.util.Log
import com.example.ourfarm.Model.APIResponse
import com.example.ourfarm.Model.UserModel
import com.example.ourfarm.View.ILoginView
import com.example.ourfarm.retrofitutil.ApiClient
import com.example.ourfarm.retrofitutil.IApi
import com.example.ourfarm.retrofitutil.RetrofitClient
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
            RetrofitClient.instance.performUserLogin(email,password)
                .enqueue(object: Callback<APIResponse>{
                    override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                        Log.d("testapi",response.body().toString())
                        if(response.body()?.error!! == "false"){
                            iLoginView.switchActivity(response.body()?.user!!)
                        }
                        else{
                            iLoginView.loginErrorFunc(response.body()!!.message)
                        }
                    }
                    override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                        iLoginView.loginErrorFunc(t.toString())
                    }
                })
        }
    }
}
