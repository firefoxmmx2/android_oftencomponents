package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by hooxin on 14-8-3.
 */
public class GestureActivity extends Activity implements GestureDetector.OnGestureListener{
    GestureDetector detector;
    Button gestureZoomButton;
    Button gesturePageButton;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture);
        //创建手势检测器
        detector=new GestureDetector(this);

        gestureZoomButton=(Button)findViewById(R.id.gestureZoomBtn);
        gestureZoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GestureActivity.this,GestureZoomActivity.class);
                startActivity(intent);
            }
        });
        gesturePageButton=(Button)findViewById(R.id.gesturePageBtn);
        gesturePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GestureActivity.this,GesturePageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(this,"onDown", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(this,"onShowPress",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(this,"onSingleTapUp",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Toast.makeText(this,"onScroll",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(this,"onLongPress",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(this,"onFling",Toast.LENGTH_SHORT).show();
        return false;
    }
}