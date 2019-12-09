package com.dgz.kttest.inf

interface BaseInterFace{
    interface View{
        fun showWaitingView()
        fun closeWaitingView()
    }

    interface Presenter{
        fun onDestroy()
    }
}