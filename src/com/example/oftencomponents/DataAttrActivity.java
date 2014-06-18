package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by hooxin on 14-6-18.
 */
public class DataAttrActivity extends Activity {
    Button bn;
    Button edit;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataattr);

        bn = (Button) findViewById(R.id.dataAttrBtn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String data = "http://www.crazyit.org";
                Uri uri = Uri.parse(data);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        edit = (Button) findViewById(R.id.dataAttrEditBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_EDIT);
                String data = "content://com.android.contacts/contacts/1";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}