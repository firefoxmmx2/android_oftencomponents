package com.example.oftencomponents;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by hooxin on 14-7-16.
 */
public class CanvasPicActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasPicView(this));
    }
}