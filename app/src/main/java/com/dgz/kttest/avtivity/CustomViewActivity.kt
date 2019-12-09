package com.dgz.kttest.avtivity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dgz.kttest.R
import com.dgz.kttest.fragment.customView.FgClock
import com.dgz.kttest.fragment.customView.FgSnow
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : AppCompatActivity() {
    private lateinit var fm: FragmentManager
    private lateinit var fragmentType:ViewType

    private lateinit var fg_snow:FgSnow
    private lateinit var fg_clock:FgClock


    enum class ViewType{
        Snow,Clock
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("test","CustomViewActivity onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        initView()
        initClick()
    }

    override fun onStart() {
        super.onStart()
        Log.e("test","CustomViewActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("test","CustomViewActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("test","CustomViewActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("test","CustomViewActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("test","CustomViewActivity onDestroy")
    }


    private fun initClick() {
        btn_change_fragment.setOnClickListener {
            val ft: FragmentTransaction = fm.beginTransaction()
            fragmentType = when(fragmentType){
                ViewType.Snow->{
                    ft.hide(fg_snow)
                    ft.show(fg_clock)
                    ViewType.Clock
                }
                ViewType.Clock->{
                    ft.hide(fg_clock)
                    ft.show(fg_snow)
                    ViewType.Snow
                }
            }
            ft.commit()
        }

        btn_add.setOnClickListener {
            Log.e("test", "添加")

            fm.beginTransaction()
                    .add(R.id.fg_container, fg_clock, "two")
                    .hide(fg_snow)
                    .show(fg_clock)
                    .commit()
            fragmentType = ViewType.Clock
        }

        btn_repalce.setOnClickListener {
            Log.e("test", "替换")
            fm.beginTransaction()
                    .replace(R.id.fg_container,fg_snow,"one")
                    .show(fg_snow)
                    .commit()
            fragmentType = ViewType.Snow
        }

    }



    private fun initView() {
        fg_clock = FgClock()
        fg_snow = FgSnow()
        fm = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fg_container, fg_snow, "one")
                .add(R.id.fg_container, fg_clock, "two")
                .show(fg_snow)
                .commit()
        fragmentType = ViewType.Snow
    }




}