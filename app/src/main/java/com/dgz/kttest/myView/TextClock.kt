package com.dgz.kttest.myView

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*
import kotlin.math.log
import kotlin.properties.Delegates

class TextClock @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):View(context,attrs,defStyleAttr){
    private val mPaint: Paint = createPaint(alpha = 160)//浅色画笔
    private val mBigPaint: Paint = createPaint(alpha = 255)//白色画笔
    private val mMaskPaint:Paint = createPaint(alpha = 255,color = Color.GREEN)
    //控件宽高
    private var vWidth:Float by Delegates.notNull()
    private var vHeight:Float by Delegates.notNull()

    //当前时间偏移角度
    private var mHourDeg:Float = 0f
    private var mMinuteDeg:Float = 0f
    private var mSecondDeg:Float = 0f

    //时分秒圈的半径
    private var mHourR:Float by Delegates.notNull()
    private var mMinuteR:Float by Delegates.notNull()
    private var mSecondR:Float by Delegates.notNull()

    private var hour:Int =0
    private var minute: Int =0
    private var second: Int =0

    private var valueAnimator:ValueAnimator by Delegates.notNull()
    private var xfermode: PorterDuffXfermode?= null



    init {
        //处理动画，声明全局的处理器
        valueAnimator  = ValueAnimator.ofFloat(1f,0f)//由6降到1
        valueAnimator.duration = 400
        valueAnimator.interpolator = LinearInterpolator()//插值器设为线性
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        vWidth = (measuredWidth-paddingLeft-paddingRight).toFloat()
        vHeight= (measuredHeight-paddingTop-paddingBottom).toFloat()

        mHourR = vWidth * 0.17f
        mMinuteR = vWidth * 0.29f
        mSecondR = vWidth * 0.42f
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(canvas==null)return
        canvas.drawColor(Color.BLACK)
        canvas.save()
        canvas.translate(vWidth/2,vHeight/2)//平移坐标原点到画布中心
        drawCenterInfo(canvas)         //中间文字
        mPaint.textSize =vWidth * 0.027f//
        mBigPaint.textSize =vWidth * 0.027f//
        drawHourCircle(canvas,hour)    //绘制 时 圆圈
        drawMinuteCircle(canvas,minute)//绘制 分 圆圈
        drawSecondCircle(canvas,second)//绘制 秒 圆圈

        canvas.restore()//恢复属性
        drawMask(canvas)//画个圆形框

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun drawMask(canvas: Canvas) {
        xfermode =  PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        mMaskPaint.alpha = 255
        mMaskPaint.style = Paint.Style.FILL
        mMaskPaint.isAntiAlias = true
        val count = canvas.saveLayer(0f,0f,vWidth,vHeight,mMaskPaint)
        canvas.drawColor(Color.WHITE)
        mMaskPaint.xfermode = xfermode
        mMaskPaint.color=Color.RED
        canvas.drawCircle(vWidth/2f, vHeight/2f, vWidth/2f, mMaskPaint)
        mMaskPaint.reset()
    }


    fun flashTime(){
        Calendar.getInstance().run{
            hour = get(Calendar.HOUR)
            minute = get(Calendar.MINUTE)
            second = get(Calendar.SECOND)
            valueAnimator.removeAllUpdateListeners()
            valueAnimator.addUpdateListener {
                val av = (it.animatedValue as Float)
                if (minute ==0&&second==0){
                    mHourDeg = av
                }
                if (second==0){
                    mMinuteDeg = av
                }
                mSecondDeg = av
                invalidate()
            }
            valueAnimator.start()
        }


    }


    private fun drawHourCircle(canvas: Canvas,hour:Int) {
        canvas.save()
        canvas.rotate(-360/12f*(hour.toFloat()-1f-mHourDeg))//先把画布转角度，因为没一次都要从一点开始画

        for (i in 1 until 13) {
            canvas.drawText("${(i).toText()}点", mHourR, mPaint.getCenteredY(), if(hour==i) mBigPaint else mPaint)
            canvas.rotate(360/12f)//转个角度
        }
        canvas.restore()
    }

    private fun drawMinuteCircle(canvas: Canvas, minute: Int) {
        canvas.save()
        canvas.rotate(-360/60f*(minute.toFloat()-mMinuteDeg))//先把画布转角度,因为没一次都要从0分开始画

        for(i in 0 until 60){
            canvas.drawText("${(i).toText()}分", mMinuteR, mPaint.getCenteredY(), if(minute==i&&mMinuteDeg==0f) mBigPaint else mPaint)
            canvas.rotate(360/60f)//转个角度
        }
        canvas.restore()

    }
    private fun drawSecondCircle(canvas: Canvas, second: Int) {
        canvas.save()
        canvas.rotate(-360/60f*(second.toFloat()-mSecondDeg))//先把画布转角度,因为没一次都要从0秒开始画

        for(i in 0 until 60){
            canvas.drawText("${(i).toText()}秒", mSecondR, mPaint.getCenteredY(), if(second==i&&mSecondDeg==0f) mBigPaint else mPaint)
            canvas.rotate(360/60f)//转个角度
        }
        canvas.restore()

    }

    private fun drawCenterInfo(canvas: Canvas) {
        Calendar.getInstance().apply {
            val hour = get(Calendar.HOUR_OF_DAY)
            val minute = get(Calendar.MINUTE)
            mBigPaint.textSize = mHourR * 0.4f//设置一下文字大小
            canvas.drawText("$hour:$minute", 0f,mBigPaint.getBottomedY(), mBigPaint)
            //绘制月份、星期
            val month = (this.get(Calendar.MONTH) + 1).let {
                if (it < 10) "0$it" else "$it"
            }
            val day = this.get(Calendar.DAY_OF_MONTH)
            val dayOfWeek = (get(Calendar.DAY_OF_WEEK) - 1).toText()
            mBigPaint.textSize = mHourR * 0.16f
            canvas.drawText("$month.$day 星期$dayOfWeek", 0f,mBigPaint.getToppedY(), mBigPaint)
        }
    }


    /**
     * 扩展获取绘制文字时在x轴上 垂直居中的y坐标
     */
    private fun Paint.getCenteredY(): Float {
        return this.fontSpacing / 2 - this.fontMetrics.bottom
    }

    /**
     * 扩展获取绘制文字时在x轴上 贴紧x轴的上边缘的y坐标
     */
    private fun Paint.getBottomedY(): Float {
        return -this.fontMetrics.bottom
    }

    /**
     * 扩展获取绘制文字时在x轴上 贴近x轴的下边缘的y坐标
     */
    private fun Paint.getToppedY(): Float {
        return -this.fontMetrics.ascent//baseLine距离字符之下的距离
    }

    /**
     * 数字转换文字
     */
    private fun Int.toText(): String {
        var result = ""
        val iArr = "$this".toCharArray().map { it.toString().toInt() }


        //处理 10，11，12.. 20，21，22.. 等情况
        if (iArr.size > 1) {
            if (iArr[0] != 1) {
                result += NUMBER_TEXT_LIST[iArr[0]]
            }
            result += "十"
            if (iArr[1] > 0) {
                result += NUMBER_TEXT_LIST[iArr[1]]
            }
        } else {
            result = NUMBER_TEXT_LIST[iArr[0]]
        }

        return result
    }

    private  fun createPaint(color:Int = Color.WHITE,alpha:Int = 255):Paint{
        return Paint().apply{
            this.color= Color.WHITE
            this.isAntiAlias= true
            this.alpha = alpha
            this.textAlign = Paint.Align.CENTER//文字居中
            this.style = Paint.Style.FILL
        }
    }


    companion object {
        private val NUMBER_TEXT_LIST = listOf(
                "零",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六",
                "七",
                "八",
                "九",
                "十"
        )
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e("dispatchTouchEvent", "ACTION_DOWN  X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
            }
            MotionEvent.ACTION_MOVE->Log.e("dispatchTouchEvent","ACTION_MOVE  X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
            MotionEvent.ACTION_UP->Log.e("dispatchTouchEvent","ACTION_UP    X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
            MotionEvent.ACTION_CANCEL->Log.e("dispatchTouchEvent","ACTION_CANCEL X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
        }
        val res:Boolean = super.dispatchTouchEvent(event)
        Log.e("dispatchTouchEvent",res.toString())
        return res
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                Log.e("test", "ACTION_DOWN  X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
                return true
            }
            MotionEvent.ACTION_MOVE->Log.e("test","ACTION_MOVE  X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
            MotionEvent.ACTION_UP->Log.e("test","ACTION_UP    X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
            MotionEvent.ACTION_CANCEL->Log.e("test","ACTION_CANCEL X=$x Y=$y rawX=${event.rawX} rawY=${event.rawY}")
        }


        return super.onTouchEvent(event)
    }


































}