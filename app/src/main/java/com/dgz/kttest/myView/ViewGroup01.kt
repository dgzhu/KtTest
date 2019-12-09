package com.dgz.kttest.myView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class ViewGroup01 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):LinearLayout(context,attrs,defStyleAttr){

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->Log.e("test2","ViewGroup_01 dispatchTouchEvent MotionEvent.ACTION_DOWN  ")
            MotionEvent.ACTION_MOVE->Log.e("test2","ViewGroup_01 dispatchTouchEvent MotionEvent.ACTION_MOVE  ")
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup_01 dispatchTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup_01 dispatchTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.dispatchTouchEvent(ev)
        return res
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->Log.e("test2","ViewGroup_01 onInterceptTouchEvent MotionEvent.ACTION_DOWN  ")
            MotionEvent.ACTION_MOVE->{
                Log.e("test2","ViewGroup_01 onInterceptTouchEvent MotionEvent.ACTION_MOVE  ")
            }
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup_01 onInterceptTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup_01 onInterceptTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.onInterceptTouchEvent(ev)
        return res
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e("test2","ViewGroup_01 onTouchEvent MotionEvent.ACTION_DOWN  ")
            }
            MotionEvent.ACTION_MOVE->Log.e("test2","ViewGroup_01 onTouchEvent MotionEvent.ACTION_MOVE  ")
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup_01 onTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup_01 onTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.onTouchEvent(event)
        return res
    }

}

