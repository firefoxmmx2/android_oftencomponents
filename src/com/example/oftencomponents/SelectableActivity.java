package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hooxin on 14-6-9.
 */
public class SelectableActivity extends Activity {
    Button bn;
    EditText selectCityEdit;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectactivity);

        bn=(Button)findViewById(R.id.selectCityBtn);
        selectCityEdit=(EditText)findViewById(R.id.selectCityEdit);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectableActivity.this,SelectableSelectActivity.class);
                //0是请求码
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0 && resultCode==0){
            Bundle bdata=data.getExtras();
            String resultCity=bdata.getString("city");
            selectCityEdit.setText(resultCity);
        }
    }
}