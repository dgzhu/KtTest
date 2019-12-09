package com.dgz.kttest.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dgz.kttest.LogUtil;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback ,Runnable{


    private SurfaceHolder mHolder;
    //用于绘图的canvas
    private Canvas mCanvas;
    private Paint mPaint;
    //子线程标志位
    private boolean mIsDrawing;
    int c = 0;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d("test","init");
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("test","surfaceCreated");
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("test","surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d("test","surfaceDestroyed");
        mIsDrawing = false;
    }



    @Override
    public void run() {
        while (mIsDrawing&&mCanvas!=null) {
            try {
                mCanvas = mHolder.lockCanvas();
                mCanvas.drawText(String.valueOf(c), 0, 0, mPaint);
                c++;
            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                if (null != mCanvas) {
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
            Log.d("test","run");
        }
    }

}
