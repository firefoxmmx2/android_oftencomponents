package com.example.oftencomponents;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hooxin on 14-6-6.
 */
public class HandlerActivity extends Activity {
    int[] imageIds =new int[]{
            R.drawable.close,
            R.drawable.close1,
            R.drawable.close2,
            R.drawable.close3,
            R.drawable.ic_launcher
    };
    int currentImageId=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);

        final ImageView show=(ImageView)findViewById(R.id.handlerImageView);
        final Handler myHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x1233){
                    show.setImageResource(imageIds[currentImageId++]);
                    if(currentImageId >=5) {
                        currentImageId=0;
                    }
                }
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=0x1233;
                myHandler.sendMessage(msg);
            }
        },0,800);
    }
}