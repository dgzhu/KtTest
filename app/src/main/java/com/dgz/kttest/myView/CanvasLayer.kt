package com.dgz.kttest.myView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class CanvasLayer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): View(context,attrs,defStyleAttr) {
    private lateinit var mPaint:Paint
    private lateinit var xfermode: PorterDuffXfermode
    private var vWidth: Float  by Delegates.notNull()
    private var vHeight: Float  by Delegates.notNull()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        vWidth = (measuredWidth-paddingLeft-paddingRight).toFloat()
        vHeight= (measuredHeight-paddingTop-paddingBottom).toFloat()
        mPaint = Paint()
        mPaint.alpha = 255
        mPaint.color = Color.BLACK
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null)return
        xfermode =  PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        canvas.drawColor(Color.GREEN)
        canvas.saveLayer(0F, 0F, width.toFloat(), width.toFloat(), mPaint)
        canvas.drawColor(Color.parseColor("#a8000000"))
        mPaint.setXfermode(xfermode)
        //中间的透明的圆
        canvas.drawCircle(width/ 2f, height / 2f, width/ 2f, mPaint)
        //白色的圆边框
        mPaint.style = Paint.Style.STROKE
        canvas.drawCircle(width / 2f, height / 2f, width/ 4f, mPaint)
        /*mPaint.color = Color.RED
        mPaint.alpha = 255
        canvas.drawCircle(vWidth/2,vHeight/2,vWidth/2-10,mPaint)

        mPaint.xfermode= xfermode
        mPaint.color = Color.YELLOW
        mPaint.alpha = 255
        canvas.drawCircle(vWidth/2,vHeight/2,vWidth/4,mPaint)*/

    }

}