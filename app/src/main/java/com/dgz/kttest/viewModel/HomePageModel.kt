package com.dgz.kttest.viewModel

import android.content.Context
import com.dgz.kttest.inf.HomePageInf

class HomePageModel(var mView:HomePageInf.View?):HomePageInf.Presenter {

    lateinit var context:Context

    override fun onDestroy(){
        mView = null
    }



}