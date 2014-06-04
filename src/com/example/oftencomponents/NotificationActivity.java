package com.example.oftencomponents;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by hooxin on 14-6-4.
 */
public class NotificationActivity extends Activity {
    static final int NOTIFICATION_ID = 0x1123;

    private Button notificationAddButton, notificationRemoveButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        notificationAddButton = (Button) findViewById(R.id.notificationAddBtn);
        notificationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(NotificationActivity.this,MainActivity.class);
//                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
//                Notification notify = new Notification();
//                notify.icon = R.drawable.ic_launcher;
//                notify.tickerText = "启动其他Activity的通知" ;
//                notify.when = System.currentTimeMillis();
////                notify.defaults = Notification.DEFAULT_SOUND;
//                notify.defaults = Notification.DEFAULT_ALL;
//                notify.setLatestEventInfo(NotificationActivity.this,"普通通知","点击查看",pi);
//
//                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                notificationManager.notify(NOTIFICATION_ID,notify);


                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);

                Notification.Builder nbuilder = new Notification.Builder(NotificationActivity.this);
                Notification notify = nbuilder.setSmallIcon(R.drawable.ic_launcher)
                        .setTicker("启动其他Activity的通知")
                        .setWhen(System.currentTimeMillis())
                        .setContentTitle("普通通知")
                        .setContentText("屠龙宝刀，点击就送")
                        .setAutoCancel(true)
                        .setContentIntent(pi)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setDeleteIntent(pi).build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID,notify);
            }
        });

        notificationRemoveButton = (Button) findViewById(R.id.notificationRemoveBtn);
        notificationRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancel(NOTIFICATION_ID);
            }
        });
    }
}