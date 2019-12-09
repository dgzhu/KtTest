package com.dgz.kttest.fragment.customView

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dgz.kttest.R
import com.dgz.kttest.myView.TextClock
import java.util.*
import kotlin.concurrent.timer

class FgClock :Fragment(){

    private var mTimer: Timer? = null
    var isRunning:Boolean = false
    lateinit var textClock: TextClock


    override fun onAttach(context: Context?) {
        Log.e("test","时钟View onAttach")

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("test","时钟View onCreate")

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("test","时钟View onViewCreated")

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.e("test","时钟View onActivityCreated")

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.e("test","时钟View onStart")
        super.onStart()
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("test","时钟View onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fg_custom_clock,container,false)
        initView(view)
        return view
    }

    private fun initView(view:View) {
        textClock = view.findViewById(R.id.clock)
        startClock()
    }

    fun startClock(){
        if(!isRunning){
            isRunning = true
            mTimer = timer(period = 1000) {
                activity?.runOnUiThread {
                    textClock.flashTime()
                }
            }
        }
    }

    override fun onResume() {
        Log.e("test","时钟View onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e("test","时钟View onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e("test","时钟View onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.e("test","时钟View onDestroyView")

        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.e("test","时钟View onDestroy")
        super.onDestroy()
        if(isRunning)mTimer?.cancel()
    }

    override fun onDetach() {
        Log.e("test","时钟View onDetach")
        super.onDetach()
    }

}