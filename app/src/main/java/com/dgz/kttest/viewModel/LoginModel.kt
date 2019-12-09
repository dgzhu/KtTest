package com.dgz.kttest.viewModel

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import com.dgz.kttest.avtivity.HomePageActivity

class LoginModel constructor(userId:String,pwd:String,context:Context) {
    val userIdOb = ObservableField<String>(userId)
    val pwdOb = ObservableField<String>(pwd)
    var context: Context = context

    fun onUserIdChanged(s: CharSequence, start: Int, before: Int, count: Int){
        userIdOb.set(s.toString())
    }

    fun onPwdChanged(s: CharSequence, start: Int, before: Int, count: Int){
        pwdOb.set(s.toString())
    }

    fun login(){
        context.startActivity(Intent(context, HomePageActivity::class.java))
    }



}