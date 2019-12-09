package com.dgz.kttest

import android.util.Log

class LogUtil{
    companion object {
        fun log(str: String, tag:String ="test"){
            Log.e(tag,str)
        }
        fun log(str: String){
            Log.e("test",str)
        }
    }
}