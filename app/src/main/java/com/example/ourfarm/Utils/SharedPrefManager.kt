package com.example.ourfarm.Utils

import android.content.Context
import com.example.ourfarm.Model.UserInfor
import com.example.ourfarm.Model.UserModel

class SharedPrefManager private constructor(private val mCtx: Context) {
    val isLoggedIn: Boolean
        get(){
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }
    val user: UserInfor
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return UserInfor(
                sharedPreferences.getInt("customer_id", -1),
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getString("name", null)!!,
                sharedPreferences.getBoolean("role", false),
                sharedPreferences.getString("username", null)!!,
            )
        }
    fun saveUser(user: UserInfor) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("customer_id", user.customer_id)
        editor.putString("email", user.email)
        editor.putString("name", user.name)
        editor.putString("username", user.username)
        editor.putBoolean("role", user.role)
        editor.apply()

    }
    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}