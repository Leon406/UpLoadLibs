package com.luck.picture.lib.widget;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * @author Leon
 * @date 2017-12-18
 * @desc 相机预览
 */
public class CameraPreviewView extends SurfaceView implements
        SurfaceHolder.Callback{
    private static final String TAG = "CameraPreviewView";
  
    private  SurfaceHolder holder;
    private Camera mCamera;

    public CameraPreviewView(Context context) {
        this(context,null);
    }

    public CameraPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        Log.i(TAG, "new View ...");  
  
        holder = getHolder();
        holder.addCallback(this);  
    }  
  
    @Override  
    public void surfaceCreated(SurfaceHolder arg0) {  
        Log.i(TAG, "surfaceCreated...");  
        if (mCamera == null) {  
            mCamera = Camera.open();
            mCamera.setDisplayOrientation(90);
            try {  
                mCamera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();  
            }  
        }  
    }  
  
    @Override  
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {  
        Log.i(TAG, "surfaceChanged...");
        mCamera.startPreview();
    }  
  
    @Override  
    public void surfaceDestroyed(SurfaceHolder arg0) {  
        Log.i(TAG, "surfaceDestroyed...");
        if (mCamera != null) {  
            mCamera.release();
            mCamera = null;  
        }  
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.i(TAG, "surfaceDestroyed...");
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
        super.onDetachedFromWindow();
    }
}