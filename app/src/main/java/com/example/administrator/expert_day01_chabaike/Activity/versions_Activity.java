package com.example.administrator.expert_day01_chabaike.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.expert_day01_chabaike.R;

public class versions_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_versions_);
    }
}
