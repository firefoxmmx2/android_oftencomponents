package com.example.oftencomponents;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hooxin on 14-7-18.
 */
public class WarpActiviy extends Activity {
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this, R.drawable.elsa));
    }

    private class MyView extends View {

        private final int WIDTH = 20;
        private final int HEIGHT = 20;
        //记录该图片上包含441个顶点
        private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
        //定义一个数组，保存BITMAP上的21×21个点的坐标
        private final float[] verts = new float[COUNT * 2];
        //定义一个数组，记录bitmap上的21×21个点经过的扭曲后的坐标
        //对图片进行扭曲的关键就是修改该数组里面的元素的值
        private final float[] orig = new float[COUNT * 2];

        public MyView(Context context, int resourceId) {
            super(context);
            setFocusable(true);
            //加载图片
            bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            //获取图片的高度和宽度
            float bitmapWidth = bitmap.getWidth();
            float bitmapHeiht = bitmap.getHeight();
            int index = 0;
            for (int y = 0; y <= HEIGHT; y++) {
                float fy = bitmapHeiht * y / HEIGHT;
                for (int x = 0; x <= WIDTH; x++) {
                    float fx = bitmapWidth * x / WIDTH;
//                    初始化orig，verts数组
//                      初始化后，orig，verts两个数组均匀地保存了21×21个点的x,y坐标
                    orig[index*2+0]=verts[index*2+0]=fx;
                    orig[index*2+1]=verts[index*2+1]=fy;
                    index+=1;
                }
            }
            setBackgroundColor(Color.WHITE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //对bitmap按照verts数组进行扭曲
            //从第一个点（由第五个参数0控制）开始扭曲
            canvas.drawBitmapMesh(bitmap,WIDTH,HEIGHT,verts,0,null,0,null);
        }

        private void warp(float cx,float cy) {
            for(int i=0;i<COUNT*2;i+=2){
                float dx=cx-orig[i+0];
                float dy=cy-orig[i+1];
                float dd=dx*dx+dy*dy;
                //计算每个坐标点与当前点（cx,cy）之间的距离
                float d=(float)Math.sqrt(dd);
                //计算扭曲度，区里当前点(cx,cy)越远扭曲度越小
                float pull=80000/((float)(dd * d));
                //对verts数组（保存bitmap上面21×21个点经过你去后的坐标）重新赋值
                if(pull >=1){
                    verts[i+0]=cx;
                    verts[i+1]=cy;
                } else {
                    //控制各顶点向触摸事件发生点偏移
                    verts[i+0]=orig[i+0]+dx*pull;
                    verts[i+1]=orig[i+1]+dy*pull;
                }
            }
            //通知view组件重绘
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            warp(event.getX(),event.getY());
            return true;
        }
    }
}

