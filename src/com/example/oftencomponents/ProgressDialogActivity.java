package com.example.oftencomponents;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by hooxin on 14-6-3.
 */
public class ProgressDialogActivity extends Activity {
    private ProgressDialog pd;
    private Handler handler;
    private int progressStatus = 0;
    private int MAX_PROGRESS_STATUS = 100;
    private boolean loop = true;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressdialog);

        pd = new ProgressDialog(this);
        pd.setMax(MAX_PROGRESS_STATUS);
        pd.setProgress(0);
//        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //进度条
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER); //转圈
        pd.setCancelable(true);
        pd.setTitle("进度对话框");
        pd.setMessage("当前进度");
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                loop = false;
            }
        });
        pd.show();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    pd.setProgress(progressStatus);
                } else if (msg.what == 0x112) {
                    pd.dismiss();
                }
                super.handleMessage(msg);
            }
        };


        Thread t = new Thread() {
            @Override
            public void run() {
                while (loop && progressStatus <= MAX_PROGRESS_STATUS) {
                    Message msg = new Message();
                    msg.what = 0x111;
                    progressStatus += 5;
                    handler.sendMessage(msg);

                    if (progressStatus >= MAX_PROGRESS_STATUS) {
                        Message msg2=new Message();
                        msg2.what = 0x112;
                        handler.sendMessage(msg2);
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t.start();

    }
}