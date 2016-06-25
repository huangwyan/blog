package com.example.administrator.expert_day01_chabaike.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.SqlUtils.DBUtils;
import com.example.administrator.httplibs.HttpHelper;
import com.example.administrator.httplibs.Request;
import com.example.administrator.httplibs.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class DetailsActivity extends AppCompatActivity {
    private TextView details_title, details_time, details_keywords, details_up;//,details_down;
    private Toolbar details_tb;
    private long details_id;
    private String title;
    private  DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);
        details_tb = (Toolbar) findViewById(R.id.details_tb);
        details_tb.setBackgroundColor(Color.GRAY);
        details_tb.setLogo(R.mipmap.ic_launcher);
        details_tb.setTitle("热点详情");
        details_tb.inflateMenu(R.menu.toolbar_menu);
        Intent intent = getIntent();
        details_id = intent.getLongExtra("id", 0);
        title = intent.getStringExtra("title");
        String url = "http://www.tngou.net/api/top/show?id=" + details_id;
        dbUtils = new DBUtils(getApplicationContext());
        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("url",url);
        long id=dbUtils.insert("record",values);
        System.out.println("浏览的数据库表插入的id"+id);
      details_tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String url = "http://www.tngou.net/api/top/show?id=" + details_id;
                switch (item.getItemId()) {
                    case R.id.collect_toolbar:
                        //Toast.makeText(getApplicationContext(), "已经收藏过了", Toast.LENGTH_SHORT).show();
                       // DBUtils dbUtils = new DBUtils(getApplicationContext());
                       // Cursor cursor = dbUtils.queryAll();
                        //String cursortitle;
                        DBUtils dbUtils = new DBUtils(getBaseContext());
                        ContentValues values = new ContentValues();
                               values.put("url", url);
                               values.put("title", title);
                                long id=dbUtils.insert("collect",values);
                        Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                        System.out.println(id+"==============数据库插入操作");
//                        while (cursor.moveToNext()) {
//                            System.out.println("进入curswor------");
//                            int index = cursor.getColumnIndex("title");
//                            cursortitle = cursor.getString(index);
//                            if (cursortitle.equals(title)) {
//                                Toast.makeText(getApplicationContext(), "已经收藏过了", Toast.LENGTH_SHORT).show();
//                            } else {
//                                ContentValues values = new ContentValues();
//                                values.put("url", url);
//                                values.put("title", title);
//                                dbUtils.insert(values);
//                                Toast.makeText(getApplicationContext(), "收藏成功", Toast.LENGTH_SHORT).show();
//                            }
//                        }

                        break;
                }


                return false;
            }
        });
        System.out.println("===========" + details_id);
        //getHttpForDetail(details_id);
        initView();

    }

    private void initView() {
        details_title = (TextView) findViewById(R.id.details_title);
        details_time = (TextView) findViewById(R.id.details_time);
        details_keywords = (TextView) findViewById(R.id.details_keywords);
        details_up = (TextView) findViewById(R.id.details_up);
        // details_down= (TextView) findViewById(R.id.details_down);
        // details_img= (ImageView) findViewById(R.id.datails_img);
        //getHttpForDetail(details_id);
        String url = "http://www.tngou.net/api/top/show?id=" + details_id;
        System.out.println(url);
        StringRequest sr = new StringRequest(url, Request.Method.GET, new StringRequest.Callback<String>() {
            @Override
            public void onEror(Exception e) {

            }

            @Override
            public void onResonse(String response) {
                if (response != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        final String title = object.getString("title");
                        System.out.println("-----------" + title);
                        final String timestr = object.getString("time");
                        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        // final String time=sdf.format(timestr);
                        //final String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestr);
                        final String keyword = object.getString("keywords");
                        final String message = object.getString("message");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(Thread.currentThread().getName());
                                details_title.setText(title);
                                details_time.setText(timestr);
                                details_keywords.setText(keyword);
                                details_up.setText(message);
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        HttpHelper.addRequest(sr);

    }
}
