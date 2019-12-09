package com.dgz.kttest.avtivity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dgz.kttest.R
import com.dgz.kttest.databinding.ActivityLoginBinding
import com.dgz.kttest.viewModel.LoginModel





class LoginActivity : AppCompatActivity()  {
    private lateinit var loginModel:LoginModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView(){
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginModel= LoginModel("admin","",this)
        binding.loginModel = loginModel
    }

    override fun onPostResume() {
        super.onPostResume()
        while(true){
            
        }
    }
}
