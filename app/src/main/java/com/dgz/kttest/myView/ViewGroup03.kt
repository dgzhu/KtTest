package com.dgz.kttest.myView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dgz.kttest.entity.ViewConfig

class ViewGroup03 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):LinearLayout(context,attrs,defStyleAttr){

    val config: ViewConfig = ViewConfig()
    var lastX:Float = 0.0f
    var lastY:Float = 0.0f
    var dx = 0.0f
    var dy = 0.0f
    var parentHeight = 0
    var parentWidth = 0
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val p: ViewGroup = parent as ViewGroup
        parentHeight = p.measuredHeight
        parentWidth = p.measuredWidth
        super.onLayout(changed, l, t, r, b)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN->{

            }
            MotionEvent.ACTION_MOVE->Log.e("test2","ViewGroup03 dispatchTouchEvent MotionEvent.ACTION_MOVE  ")
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup03 dispatchTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup03 dispatchTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.dispatchTouchEvent(ev)
        return res
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(ev==null)return super.onInterceptTouchEvent(ev)

        when(ev.action){
            MotionEvent.ACTION_DOWN->{
                lastX = ev.rawX
                lastY = ev.rawY
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE->{
                dx= ev.rawX -lastX
                dy = ev.rawY-lastY
                if(dx*dx>80||dy*dy>80)return true

            }
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup03 onInterceptTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup03 onInterceptTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.onInterceptTouchEvent(ev)
        return res
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event==null)return super.onTouchEvent(event)
        Log.e("test2","geX=$x getY=$y event.rawX=${event.rawX}  event.rawY=${event.rawY} $dx $dy")
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                lastX = event.rawX
                lastY = event.rawY
                //按下的时候设为点击
                return true
            }
            MotionEvent.ACTION_MOVE->{
                dx= event.rawX -lastX
                dy = event.rawY-lastY

                when{
                    x+dx<=0.0f->x=0.0f
                    (x+dx+width)>=(parentWidth)->x=(parentWidth-width).toFloat()
                    else->x+=dx
                }
                when{
                    y+dy<=0.0f->y=0.0f
                    y+dy+height>=parentHeight->y=(parentHeight-height).toFloat()
                    else->y+=dy
                }

                lastX = event.rawX
                lastY = event.rawY
                return true
            }
            MotionEvent.ACTION_UP->Log.e("test2","ViewGroup03 onTouchEvent MotionEvent.ACTION_UP  ")
            MotionEvent.ACTION_CANCEL->Log.e("test2","ViewGroup03 onTouchEvent MotionEvent.ACTION_CANCEL  ")
        }
        val res = super.onTouchEvent(event)
        return res
    }

}

