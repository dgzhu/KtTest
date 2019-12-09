package com.dgz.kttest.myView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import kotlin.properties.Delegates

class Snow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): View(context,attrs,defStyleAttr){

    private lateinit var mPaint:Paint
    private var centerX:Float by Delegates.notNull()
    private var centerY:Float by Delegates.notNull()
    private var radius :Float by Delegates.notNull()

    private lateinit var outPaint:Paint

    init{
        setLayerType(LAYER_TYPE_SOFTWARE,null)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        centerX = width/2f+left
        centerY = height/2f+top
        radius = Math.min(width/4f, height/4f)
        outPaint= Paint(Paint.ANTI_ALIAS_FLAG).apply{
            color = Color.parseColor("#e6e8db")
            maskFilter=BlurMaskFilter(radius,BlurMaskFilter.Blur.SOLID)//外围模糊遮罩
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null)return
        mPaint = Paint().apply {
            color= Color.WHITE
            isAntiAlias = true
            style = Paint.Style.FILL
        }

        canvas.drawColor(Color.BLACK)
        canvas.drawCircle(centerX,centerY,radius,outPaint)
    }


}