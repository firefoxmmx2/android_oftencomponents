package com.example.oftencomponents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hooxin on 14-8-1.
 */
public class SqliteActivity extends Activity {
    MySqliteDatabaseHelper helper;
    Button insert;
    Button search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_layout);

        helper = new MySqliteDatabaseHelper(this, "myDict.db", 1);
        insert = (Button) findViewById(R.id.sqliteAddWordBtn);
        search = (Button) findViewById(R.id.sqliteSearchBtn);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = ((EditText) findViewById(R.id.sqliteEd1)).getText().toString();
                String detial = ((EditText) findViewById(R.id.sqliteEd2)).getText().toString();
                insertData(helper.getWritableDatabase(), word, detial);
                Toast.makeText(SqliteActivity.this, "添加生词成功!", 8000).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = ((EditText) findViewById(R.id.sqliteEd3)).getText().toString();
                Cursor cursor = helper.getReadableDatabase().rawQuery("" +
                                "select * from dict where word like ? or detail like ?",
                        new String[]{"%" + key + "%", "%" + key + "%"});
                Bundle data = new Bundle();
                data.putSerializable("data", converCursorToList(cursor));
                Intent intent = new Intent(SqliteActivity.this, SqliteResultActivity.class);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("word", cursor.getString(1));
            map.put("detail", cursor.getString(2));
            result.add(map);
        }
        return result;
    }

    private void insertData(SQLiteDatabase db, String word, String detail) {
        db.execSQL("insert into dict values(null,?,?)", new String[]{word, detail});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null)
            helper.close();
    }
}

class MySqliteDatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL = "create table dict(_id integer primary key autoincrement,word,detail)";

    public MySqliteDatabaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("---------------------onUpdate Called---------------------");
        System.out.println("-------------->" + newVersion);
    }
}

