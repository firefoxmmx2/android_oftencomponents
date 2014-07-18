package com.example.oftencomponents;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hooxin on 14-7-18.
 */
public class SharedPreferencesActivity extends Activity {
    private SharedPreferences sharedPreferences;
    private Button writeButton;
    private Button readButton;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference);

        sharedPreferences = getSharedPreferences("crazyit", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        writeButton = (Button) findViewById(R.id.sharedPreferenceWriteDataBtn);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                editor.putString("time", df.format(new Date()));
                editor.putInt("random", (int) (Math.random() * 100));
                editor.commit();
            }
        });
        readButton = (Button) findViewById(R.id.sharedPreferenceReadDataBtn);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = sharedPreferences.getString("time", "无");
                int randNum = sharedPreferences.getInt("random", 0);
                String result = time.equals("无") ? "暂时还没有写入数据" : "写入的时间为：" + time +
                        "\n上次生成的随机数字为：" + randNum;
                Toast.makeText(SharedPreferencesActivity.this, result, 5000).show();
            }
        });


    }
}