package com.dgz.kttest.myView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import com.dgz.kttest.entity.PowerValue
import java.util.logging.Handler
import kotlin.properties.Delegates

class RadarView @JvmOverloads constructor( context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private  var imaginaryLinePain: Paint = Paint().apply {
        color= Color.GRAY
        isAntiAlias = true
        strokeWidth=2f
        style = Paint.Style.STROKE
    } //虚线画笔
    private  var pointPain: Paint = Paint().apply {
        color= Color.BLUE
        isAntiAlias = true
        style = Paint.Style.STROKE
    }         //圆点画笔
    private  var mapPain: Paint = Paint().apply {
        color= Color.parseColor("#FF0093A7")
        isAntiAlias = true
        style = Paint.Style.FILL
        alpha = 180
    }           //图画笔
    private  var textPain: Paint = Paint().apply {
        color= Color.parseColor("#FF333333")
        isAntiAlias = true
        style = Paint.Style.STROKE
        textSize = 30f
    }           //文字画笔
    private var mWidth:Int by Delegates.notNull()
    private var mHeight:Int by Delegates.notNull()
    private var centerX:Int by Delegates.notNull()
    private var centerY:Int by Delegates.notNull()
    private var mRadius:Int by Delegates.notNull()
    private var dataList:ArrayList<PowerValue> = ArrayList()
    private var stepCount:Int = 10
    init{
        dataList.add(PowerValue("耐力", 67))
        dataList.add(PowerValue("速度", 39))
        dataList.add(PowerValue("力量", 79))
        dataList.add(PowerValue("爆发", 99))
        dataList.add(PowerValue("精神", 88))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    @SuppressLint("DrawAllocation")
    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        centerX = (r-l)/2
        centerY = (b-t)/2
        mWidth = 10
        mHeight = 10

    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas==null)return
        canvas.save()
        canvas.translate(centerX.toFloat(),centerY.toFloat())
        /*canvas.drawLine(-mWidth/2f,0f,mWidth/2f,0f,imaginaryLinePain)
        canvas.drawLine(0f,-mHeight/2f,0f,mHeight/2f,imaginaryLinePain)*/

        mRadius = Math.min(mWidth/2, mHeight/2)
        //画网
        for (index in 1 until stepCount+1){
            drawImaginaryLine(canvas,index)
        }

        //画点
        val path = Path()
        for (index in 0 until dataList.size){
            drawMap(path,canvas,index)
        }
        path.lineTo(dataList.get(0).value/100f*mRadius,0f)//连接到第一个点
        canvas.drawPath(path,mapPain)
        canvas.restore()
        postDraw()

    }
    private fun postDraw(){
        if(mWidth==centerX*2&&mHeight==centerY*2)return
        mWidth+=10
        mHeight+=10
        if(mWidth>centerX*2){
            mWidth = centerX*2
        }
        if(mHeight>centerY*2){
            mHeight = centerY*2
        }

        postInvalidate()
    }

    private fun drawMap(path: Path, canvas: Canvas,count:Int) {
        val dataSize = dataList.size
        if(dataSize==0){
            return
        }
        val powerRadius = dataList.get(count).value/100f*mRadius
        var x1 = (powerRadius*Math.cos(Math.toRadians((360/dataSize*count).toDouble()))).toFloat()
        var y1 = (powerRadius*Math.sin(Math.toRadians((360/dataSize*count).toDouble()))).toFloat()
        path.lineTo(x1,y1)
        canvas.drawCircle(x1,y1,10f,pointPain)
        canvas.drawText(dataList.get(count).value.toString(),x1,y1,textPain)
    }

    private fun drawImaginaryLine(canvas: Canvas,count:Int) {
        val dataSize = dataList.size
        if(dataSize==0){
            return
        }
        val stepWidth:Float = (mRadius/stepCount*count).toFloat()
        val path = Path()
        path.moveTo(stepWidth, 0f)
        for (index in 0 until dataSize) {
            var x1 = (stepWidth*Math.cos(Math.toRadians((360/dataSize*index).toDouble()))).toFloat()
            var y1 = (stepWidth*Math.sin(Math.toRadians((360/dataSize*index).toDouble()))).toFloat()
            path.lineTo(x1,y1)
            if(count==stepCount){
                canvas.drawLine(0f, 0f, x1, y1,imaginaryLinePain)
                canvas.drawText(dataList.get(index).name,x1,y1,textPain)
            }
        }
        path.close()
        canvas.drawPath(path, imaginaryLinePain)
    }
}