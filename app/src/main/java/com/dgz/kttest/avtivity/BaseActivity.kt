package com.dgz.kttest.avtivity

import android.app.Activity
import android.os.Bundle
import com.dgz.kttest.inf.BaseInterFace

abstract class BaseActivity<T: BaseInterFace.Presenter> : Activity() {
    protected var mPresenter:T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = bindPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()//释放P层对V层的引用
        mPresenter = null//释放V层对P层的引用
    }


    abstract fun  bindPresenter():T
}