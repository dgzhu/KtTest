package com.dgz.kttest.myView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.dgz.kttest.R
import kotlin.properties.Delegates



class MyProgress @JvmOverloads constructor(context: Context,attrs: AttributeSet? = null, defStyleAttr: Int = 0):View(context,attrs,defStyleAttr){

    private var mWidth:Float by Delegates.notNull()
    private var mHeight:Float by Delegates.notNull()
    private var percent=0f
    //底部背景画笔
    private  var bgPaint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        alpha = 100
    }

    //顶部进度画笔
    private  var topPaint: Paint = Paint().apply {
        color= Color.parseColor("#FF0093A7")
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
    }

    //百分比文字画笔
    private  var textPaint: Paint = Paint().apply {
        color= Color.WHITE
        isAntiAlias = true
        style = Paint.Style.STROKE
        alpha = 150

    }

    init {
        val myAttr = context.obtainStyledAttributes(attrs, R.styleable.myProgress)
        bgPaint.color = myAttr.getColor(R.styleable.myProgress_bgColor, Color.BLACK)
        topPaint.color = myAttr.getColor(R.styleable.myProgress_topColor,Color.BLUE)
        textPaint.color = myAttr.getColor(R.styleable.myProgress_textColor,Color.RED)
        percent = myAttr.getFloat(R.styleable.myProgress_defaultPercent,5f)
        myAttr.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = (right-left).toFloat()
        mHeight = (bottom-top).toFloat()
    }




    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(canvas==null)return
        bgPaint.strokeWidth = mHeight/2.0f
        topPaint.strokeWidth = mHeight/2.0f

        var percentX = mHeight/2.0f+(mWidth-mHeight)*percent/100

        canvas.drawLine(mHeight/2.0f, mHeight / 2, mWidth-mHeight/2f, mHeight / 2, bgPaint)
        canvas.drawLine(mHeight/2.0f, mHeight / 2,percentX, mHeight / 2, topPaint)
        textPaint.textSize = mHeight/3.0f
        textPaint.textAlign = Paint.Align.RIGHT
        if(percent<10){//如果小于10%，则显示在右边，并且向右偏移一个圆角半径的位置
            textPaint.textAlign = Paint.Align.LEFT
          //  percentX+=mHeight/3
        }
        canvas.drawText(String.format("%.2f",percent)+"%",percentX+mHeight/5,mHeight*3/5,textPaint)
    }

    fun setPercent(p:Float){
        val temp = percent
        val valueAnimator:ValueAnimator = ValueAnimator.ofFloat(p-percent)
        valueAnimator.duration = 800
        valueAnimator.addUpdateListener {
            percent=temp+it.animatedValue as Float
            postInvalidate()

        }
        valueAnimator.start()
    }

    fun getPercent():Float{

        parent.requestDisallowInterceptTouchEvent(true)
        return percent
    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(event)
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}