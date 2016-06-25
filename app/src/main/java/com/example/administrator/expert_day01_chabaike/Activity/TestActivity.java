package com.example.administrator.expert_day01_chabaike.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.SqlUtils.DBUtils;

public class TestActivity extends AppCompatActivity {
    private ListView test_listview;
    private SimpleCursorAdapter adapter;
    private String sqlName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test);
        Intent intent=getIntent();
        sqlName=intent.getStringExtra("sql");
        test_listview= (ListView) findViewById(R.id.test_listview);
        initData();
    }
    private void initData() {
        DBUtils dbUtils=new DBUtils(this);
        Cursor cursor=dbUtils.queryAll(sqlName);
        System.out.println("查询数据库");
        adapter=new SimpleCursorAdapter(this,
                R.layout.collect_item,
                cursor,
                new String[]{"title","url"},
                new int[]{R.id.collect_title,R.id.collect_url},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        System.out.println("adapter.getCount()" + adapter.getCount());
        test_listview.setAdapter(adapter);

    }
}
