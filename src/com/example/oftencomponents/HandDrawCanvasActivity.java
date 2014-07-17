package com.example.oftencomponents;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.*;

/**
 * Created by hooxin on 14-7-17.
 */
public class HandDrawCanvasActivity extends Activity {
    EmbossMaskFilter emboss;
    BlurMaskFilter blur;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handdraw);

        emboss=new EmbossMaskFilter(new float[]{1.5f,1.5f,1.5f},0.6f,6,4.2f);
        blur=new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.handdraw_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawView dv=(DrawView)findViewById(R.id.handDrawView);
        switch (item.getItemId()){
            case R.id.handDrawRed:
                dv.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case R.id.handDrawGreen:
                dv.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case R.id.handDrawBlue:
                dv.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.handDrawWidth1:
                dv.paint.setStrokeWidth(1);
                break;
            case R.id.handDrawWidth2:
                dv.paint.setStrokeWidth(3);
                break;
            case R.id.handDrawWidth3:
                dv.paint.setStrokeWidth(5);
                break;
            case R.id.handDrawBlur:
                dv.paint.setMaskFilter(blur);
                break;
            case R.id.handDrawEmboss:
                dv.paint.setMaskFilter(emboss);
                break;
        }
        return true;
    }
}

class DrawView extends View {
    float preX, preY;
    private Path path;
    public Paint paint;
    final int VIEW_WIDTH=600;
    final int VIEW_HEIGHT=800;

    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        创建一个缓冲图片
        cacheBitmap= Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
        cacheCanvas=new Canvas();
        path=new Path();
        //设置cacheCavas将会会知道内存中的cacheBitmap
        cacheCanvas.setBitmap(cacheBitmap);
        //设置画笔颜色
        paint=new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动事件的发生位置
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX=x;
                preY =y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint bmpPaint=new Paint();
        //将cacheBitmap会知道该view组件上
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        //沿着path 绘制
        canvas.drawPath(path,paint);
    }
}