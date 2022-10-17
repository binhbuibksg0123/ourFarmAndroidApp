package com.example.ourfarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ourfarm.Model.UserInfor
import com.example.ourfarm.Presenter.ILoginPresenter
import com.example.ourfarm.Presenter.LoginPresenter
import com.example.ourfarm.Utils.SharedPrefManager
import com.example.ourfarm.View.ILoginView
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(),ILoginView{
    lateinit var loginPresenter: ILoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenter(this)
        loginBtn.setOnClickListener{
            loginPresenter.loginFunc(emailLogin.text.toString(),passwordLogin.text.toString())
        }
    }

    override fun loginErrorFunc(error: String) {
        errorTv.visibility = View.VISIBLE
        errorTv.setText(error)
    }

    override fun switchActivity(user: UserInfor) {
        SharedPrefManager.getInstance(applicationContext).saveUser(user)
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}