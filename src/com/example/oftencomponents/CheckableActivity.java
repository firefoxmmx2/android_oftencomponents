package com.example.oftencomponents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

/**
 * Created by hooxin on 14-6-4.
 */
public class CheckableActivity extends Activity {
    final int MALE = 0X111;
    final int FEMAIL = 0x112;

    final int RED = 0x113;
    final int GREEN = 0X114;
    final int BLUE = 0x115;

    MenuItem[] items = new MenuItem[3];
    String[] corlorNames = new String[]{"红色", "绿色", "蓝色"};
    private EditText edit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkablemenu);

        edit = (EditText) findViewById(R.id.txt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu genderMenu = menu.addSubMenu("你的性别");
        genderMenu.setIcon(R.drawable.ic_launcher);
        genderMenu.setHeaderIcon(R.drawable.ic_launcher);
        genderMenu.setHeaderTitle("选择你的性别");
        genderMenu.add(0, MALE, 0, "男性");
        genderMenu.add(0, FEMAIL, 0, "女性");
        genderMenu.setGroupCheckable(0, true, true);
        SubMenu colorMenu = menu.addSubMenu("喜欢的颜色");
        colorMenu.setIcon(R.drawable.ic_launcher);
        colorMenu.setHeaderIcon(R.drawable.ic_launcher);
        colorMenu.setHeaderTitle("现在你最喜欢的颜色");
        items[0] = colorMenu.add(0, RED, 0, corlorNames[0]).setCheckable(true);
        items[1] = colorMenu.add(0, GREEN, 0, corlorNames[1]).setCheckable(true);
        items[2] = colorMenu.add(0, BLUE, 0, corlorNames[2]).setCheckable(true);
        items[2].setAlphabeticShortcut('u');

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MALE:
                edit.setText("你的性别为:男");
                item.setChecked(true);
                break;
            case FEMAIL:
                edit.setText("你的性别为:女");
                item.setChecked(true);
                break;
            case RED:
                if (item.isChecked()) {
                    item.setChecked(false);
                } else
                    item.setChecked(true);
                showColor();
                break;
            case GREEN:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                showColor();
                break;
            case BLUE:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                showColor();
                break;
        }
        return true;
    }

    private void showColor() {
        String result = "你喜欢的颜色有 : ";
        for (int i = 0; i < items.length; i++)
            if (items[i].isChecked())
                result += corlorNames[i] + ",";
        edit.setText(result);
    }
}