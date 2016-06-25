package com.example.administrator.expert_day01_chabaike.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.spfUtils.Pref_Utils;

public class LoadingActivity extends AppCompatActivity {
    private Handler handler=new Handler();
    private Boolean isFistRun=false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading);



        //spf=getSharedPreferences()
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFistRun = Pref_Utils.getBoolean(getApplicationContext(), "isFistRun", true);
                if (isFistRun) {
                    intent = new Intent(LoadingActivity.this, WelcomeActivity.class);
                }else{
                    intent = new Intent(LoadingActivity.this, HomeActivity.class);
                }
                startActivity(intent);
                finish();
              }
            }

            ,3000);
        }


    }
