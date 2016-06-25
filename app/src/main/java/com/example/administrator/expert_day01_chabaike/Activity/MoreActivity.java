package com.example.administrator.expert_day01_chabaike.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.administrator.expert_day01_chabaike.R;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more);
        initView();
    }

    private void initView() {
        findViewById(R.id.text_collect).setOnClickListener(this);//收藏夹
        findViewById(R.id.text_record).setOnClickListener(this);//浏览记录
        findViewById(R.id.text_versions).setOnClickListener(this);//版本信息
        findViewById(R.id.text_feedback).setOnClickListener(this);//意见反馈
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.text_collect://收藏夹
                intent.setClass(MoreActivity.this,TestActivity.class);
                intent.putExtra("sql","collect");
                startActivity(intent);
                break;
            case R.id.text_record://浏览记录
                intent.setClass(MoreActivity.this,TestActivity.class);
                intent.putExtra("sql", "record");
                startActivity(intent);
                break;
            case R.id.text_versions://版本信息
                //Intent intent=new Intent();
                intent.setClass(MoreActivity.this,versions_Activity.class);
                startActivity(intent);
                break;
            case R.id.text_feedback://意见反馈
                break;
        }
    }
}
