package com.example.administrator.expert_day01_chabaike.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.administrator.expert_day01_chabaike.Fragment.CollectOrRecordFragment;
import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.beans.TabCollect;

public class CollectActivity extends AppCompatActivity {
    private TabLayout collect_tabs;
    private ViewPager collect_viewpager;
    private TabCollect[]tabCollects=new TabCollect[]{
          new TabCollect("浏览记录","record"),
          new TabCollect("收藏","collect")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_collect);
        intiView();
    }

    private void intiView() {
        System.out.println("初始化的方法");
        collect_tabs= (TabLayout) findViewById(R.id.collect_tabs);
        collect_viewpager= (ViewPager) findViewById(R.id.collect_viewpager);
        collect_viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        collect_tabs.setupWithViewPager(collect_viewpager);
        collect_tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
    class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            CollectOrRecordFragment crf=new CollectOrRecordFragment();
            String sqlName=tabCollects[position].getSqlName();
            Bundle bundle=new Bundle();
            bundle.putString("sqlName",sqlName);
            System.out.println("getItem()方法");
            crf.setArguments(bundle);
            return crf;
        }

        @Override
        public int getCount() {
            return tabCollects.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabCollects[position].getName();
        }
    }
}
