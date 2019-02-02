package com.example.giovanni.overlay_test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Overlay_service extends Service  {
    WindowManager mWindowManager;
    LinearLayout lView;
    WindowManager wm;
    WindowManager.LayoutParams params;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Service created!",Toast.LENGTH_LONG).show();
        lView = new LinearLayout(this);
        lView.setOrientation(LinearLayout.VERTICAL);

        lView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                String log = "Touch coordinates: "+" X:"+
                        String.valueOf(e.getX()) + " - Y:" + String.valueOf(e.getY());
                Log.i("inputLog",log );
                return false;
            }
        });


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service Started!",Toast.LENGTH_LONG).show();


        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        params.gravity = Gravity.START | Gravity.LEFT;
        wm.addView(lView, params);
        wm.updateViewLayout(lView, params);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service Destroyed!",Toast.LENGTH_LONG).show();
        if(mWindowManager != null) {
            if(lView != null) mWindowManager.removeView(lView);
        }
    }




}
