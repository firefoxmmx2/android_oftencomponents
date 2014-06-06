package com.example.oftencomponents;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hooxin on 14-6-6.
 */
public class ConfigrationActivity extends Activity {
    EditText ori;
    EditText navigation;
    EditText touch;
    EditText mnc;
    Button bn;
    Button changeBtn;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);

        ori = (EditText) findViewById(R.id.ori);
        navigation = (EditText) findViewById(R.id.navigation);
        touch = (EditText) findViewById(R.id.touch);
        mnc = (EditText) findViewById(R.id.mnc);
        bn = (Button) findViewById(R.id.configurationBtn);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration cfg = getResources().getConfiguration();
                String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
                String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制" :
                        cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮控制方向" :
                                cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向" : "轨迹球控制方向";
                String mncCode = cfg.mnc + "";
                navigation.setText(naviName);
                String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏" :
                        cfg.touchscreen == Configuration.TOUCHSCREEN_FINGER ? "接收手指的触摸屏" : "触摸笔式触摸屏";
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touchName);
            }
        });

        changeBtn = (Button) findViewById(R.id.configChangeBtn);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration cfg = getResources().getConfiguration();
                Toast.makeText(ConfigrationActivity.this, cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "当前横屏" : "竖屏", Toast.LENGTH_LONG).show();
                if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //设置为横盘
                    ConfigrationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    //设置为竖屏
                    ConfigrationActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
        Toast.makeText(ConfigrationActivity.this, "系统的屏幕方向发生改变" +
                "\n修改后的屏幕方向为" + screen, Toast.LENGTH_LONG).show();
    }
}