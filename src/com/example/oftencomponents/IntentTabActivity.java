package com.example.oftencomponents;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by hooxin on 14-6-18.
 */
public class IntentTabActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("已接电话", getResources().getDrawable(R.drawable.close))
                .setContent(new Intent(this, NotificationActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("呼出电话")
                .setContent(new Intent(this, SysActionActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("未接电话")
                .setContent(new Intent(this, ProgressDialogActivity.class)));
    }
}