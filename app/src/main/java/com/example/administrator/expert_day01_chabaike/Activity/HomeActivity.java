package com.example.administrator.expert_day01_chabaike.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.expert_day01_chabaike.Fragment.ContentFragment;
import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.beans.TabInfo;


public class HomeActivity extends FragmentActivity {
    private ViewPager home_viewpager;
    private TabLayout home_tabs;
    private Toolbar home_tb;
    private TabInfo[]tabInfos=new TabInfo[]{

            new TabInfo("民生热点",1),
            new TabInfo("娱乐热点",2),
            new TabInfo("财经热点",3),
            new TabInfo("体育热点",4),
            new TabInfo("教育热点",5),
            new TabInfo("社会热点",6),

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        home_viewpager= (ViewPager) findViewById(R.id.home_viewpager);
        home_tabs= (TabLayout) findViewById(R.id.home_tabs);
        home_tb= (Toolbar) findViewById(R.id.home_tb);
        home_viewpager.setAdapter(new Home_vpAdapter(getSupportFragmentManager()));
        home_tabs.setupWithViewPager(home_viewpager);
        home_tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        home_tb.setLogo(R.mipmap.ic_launcher);
        home_tb.setTitle("茶百科");
        home_tb.inflateMenu(R.menu.more_menu);
        home_tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.more_item){
                    Intent intent=new Intent();
                    intent.setClass(getBaseContext(),MoreActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }
    public class Home_vpAdapter extends FragmentStatePagerAdapter{

        public Home_vpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ContentFragment contentFragment=new ContentFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("id",tabInfos[position].class_id);
            contentFragment.setArguments(bundle);
            return contentFragment;
        }

        @Override
        public int getCount() {
            return tabInfos.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabInfos[position].name;
        }
    }
}
