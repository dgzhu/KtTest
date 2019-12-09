package com.dgz.kttest.viewModel

import com.dgz.kttest.inf.CustomViewInf

class CustomViewModel(var mView:CustomViewInf.View?):CustomViewInf.Presenter{

    override fun onDestroy() {
        mView = null
    }

}