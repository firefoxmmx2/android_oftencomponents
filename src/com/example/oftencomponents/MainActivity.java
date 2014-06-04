package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Button progressDialogButton;
    private Button toastButton;
    private Button notificationButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        progressDialogButton = (Button) findViewById(R.id.progressDialogBtn);
        progressDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProgressDialogActivity.class);
                startActivity(intent);
            }
        });

        toastButton = (Button) findViewById(R.id.msgDialogBtn);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this,"带图片的提示信息",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                View toastView = toast.getView();
                ImageView image = new ImageView(MainActivity.this);
                image.setImageResource(R.drawable.ic_launcher);
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.addView(image);
                layout.addView(toastView);
                toast.setView(layout);
                toast.show();
            }
        });

        notificationButton = (Button) findViewById(R.id.notificationBtn);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}