package com.example.oftencomponents;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by hooxin on 14-8-3.
 */
public class GestureZoomActivity extends Activity implements GestureDetector.OnGestureListener{
    GestureDetector detector;
    ImageView imageView;
    Bitmap bitmap;
    int width,height;
//    缩放比
    float currentScale=1;
//    控制图片存放整列
    Matrix matrix;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_zoom);
        detector=new GestureDetector(this);
        imageView=(ImageView)findViewById(R.id.gestureShow);
        matrix=new Matrix();
//        获取被缩放的图片
        bitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.elsa);
//        获得位图高宽
        width= bitmap.getWidth();
        height= bitmap.getHeight();
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.elsa));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        velocityX=velocityX>4000?4000:velocityX;
        velocityX=velocityX<-4000?-4000:velocityX;
//        根据手势速度来计算缩放比,如果 velocityX>0,放大图像,否则缩小图像
        currentScale+=currentScale>0.01?currentScale:0.01f;
        matrix.reset();
        matrix.setScale(currentScale,currentScale,160,200);
        BitmapDrawable tmp=(BitmapDrawable)imageView.getDrawable();
//        如果图片还未回收,先强制回收该图片
        if (!tmp.getBitmap().isRecycled()){
            tmp.getBitmap().recycle();
        }
//        根据原始位图和matrix创建新图片
        Bitmap bitmap2=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        imageView.setImageBitmap(bitmap2);
        return true;
    }

}