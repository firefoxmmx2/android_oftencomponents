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
    private Button checkableMenuButton;
    private Button configurationButton;
    private Button handlerButton;
    private Button activityResultButton;
    private Button intentTranBundleButton;
    private Button returnHomeButton;
    private Button dataAttrStartButton;
    private Button intentTabButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        progressDialogButton = (Button) findViewById(R.id.progressDialogBtn);
        progressDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProgressDialogActivity.class);
                startActivity(intent);
            }
        });

        toastButton = (Button) findViewById(R.id.msgDialogBtn);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "带图片的提示信息",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
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

        checkableMenuButton = (Button) findViewById(R.id.checkableMenuBtn);
        checkableMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckableActivity.class);
                startActivity(intent);
            }
        });

        configurationButton = (Button) findViewById(R.id.configurationActivityBtn);
        configurationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigrationActivity.class);
                startActivity(intent);
            }
        });

        handlerButton=(Button)findViewById(R.id.handlerBtn);
        handlerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,HandlerActivity.class);
                startActivity(intent);
            }
        });

        activityResultButton=(Button)findViewById(R.id.activityResultBtn);
        activityResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SelectableActivity.class);
                startActivity(intent);
            }
        });

        intentTranBundleButton=(Button)findViewById(R.id.intentTBundleBtn);
        intentTranBundleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SysActionActivity.class);
                startActivity(intent);
            }
        });

        returnHomeButton=(Button)findViewById(R.id.returnHomeStartBtn);
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ReturnHomeActivity.class);
                startActivity(intent);
            }
        });

        dataAttrStartButton=(Button)findViewById(R.id.dataAttrStartBtn);
        dataAttrStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DataAttrActivity.class);
                startActivity(intent);
            }
        });

        intentTabButton=(Button)findViewById(R.id.intentTabBtn);
        intentTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,IntentTabActivity.class);
                startActivity(intent);
            }
        });
    }
}
