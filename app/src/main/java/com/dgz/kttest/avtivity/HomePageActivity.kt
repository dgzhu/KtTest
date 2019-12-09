package com.dgz.kttest.avtivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.dgz.kttest.R
import com.dgz.kttest.inf.HomePageInf
import com.dgz.kttest.myView.MyProgress
import com.dgz.kttest.myView.TextClock
import com.dgz.kttest.viewModel.HomePageModel
import java.util.*
import kotlin.concurrent.timer

class HomePageActivity : BaseActivity<HomePageModel>(),HomePageInf.View {

    private lateinit var tv_navigation_title: TextView
    private lateinit var imgBtn_navigation_back: ImageButton
    private lateinit var imgBtn_navigation_search: ImageButton
    private lateinit var btn_add_producer: Button
    private lateinit var btn_add_consumer: Button
    private lateinit var myProgress: MyProgress


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        initView()
    }

    private fun initView() {
        tv_navigation_title = findViewById(R.id.tv_navigation_title)
        tv_navigation_title.text = "主界面"
        imgBtn_navigation_back = findViewById(R.id.imgBtn_navigation_left)
        imgBtn_navigation_search = findViewById(R.id.imgBtn_navigation_right)
        imgBtn_navigation_search.setOnClickListener { startActivity(Intent(this,CustomViewActivity::class.java)) }
        imgBtn_navigation_back.setOnClickListener { finish() }
        btn_add_producer = findViewById(R.id.btn_add_producer)
        btn_add_consumer = findViewById(R.id.btn_add_consumer)
        myProgress = findViewById(R.id.prog_myProgress)

        btn_add_producer.setOnClickListener {
            myProgress.setPercent(Random().nextInt(100).toFloat())
        }


    }
    override fun showWaitingView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeWaitingView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindPresenter(): HomePageModel {
        return HomePageModel(this)
    }


}