package com.dgz.kttest.myView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):View(context,attrs,defStyleAttr){

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->Log.e("test2","MyView dispatchTouchEvent MotionEvent.ACTION_DOWN  ")
            MotionEvent.ACTION_MOVE->Log.e("test2","MyView dispatchTouchEvent MotionEvent.ACTION_MOVE  ")
            MotionEvent.ACTION_UP->Log.e("test2","MyView dispatchTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","MyView dispatchTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.dispatchTouchEvent(ev)
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e("test2","MyView onTouchEvent MotionEvent.ACTION_DOWN  ")
                return true
            }
            MotionEvent.ACTION_MOVE->Log.e("test2","MyView onTouchEvent MotionEvent.ACTION_MOVE  ")
            MotionEvent.ACTION_UP->Log.e("test2","MyView onTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","MyView onTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.onTouchEvent(event)
        return res
    }
}

