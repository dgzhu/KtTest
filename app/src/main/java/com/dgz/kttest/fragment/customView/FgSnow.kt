package com.dgz.kttest.fragment.customView

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dgz.kttest.R

class FgSnow : Fragment() {
    override fun onAttach(context: Context?) {
        Log.e("test","snowWiew onAttach")

        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("test","snowWiew onCreate")

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("test","snowWiew onViewCreated")

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.e("test","snowWiew onActivityCreated")

        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.e("test","snowWiew onStart")
        super.onStart()
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("test","snowWiew onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        val view:View = inflater.inflate(R.layout.fg_custom_view,container,false)
        return view
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onResume() {
        Log.e("test","snowWiew onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.e("test","snowWiew onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e("test","snowWiew onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.e("test","snowWiew onDestroyView")

        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.e("test","snowWiew onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.e("test","snowWiew onDetach")
        super.onDetach()
    }

}