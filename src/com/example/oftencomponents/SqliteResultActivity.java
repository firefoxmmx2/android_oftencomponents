package com.example.oftencomponents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by hooxin on 14-8-1.
 */
public class SqliteResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_result);
        ListView listView = (ListView) findViewById(R.id.sqliteListView);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        List<Map<String, String>> list = (List<Map<String, String>>) data.getSerializable("data");
        SimpleAdapter adapter = new SimpleAdapter(SqliteResultActivity.this, list, R.layout.sqlite_result, new String[]{"word", "detail"},
                new int[]{R.id.sqliteWordEd,R.id.sqliteDetailEd});
        listView.setAdapter(adapter);
    }
}