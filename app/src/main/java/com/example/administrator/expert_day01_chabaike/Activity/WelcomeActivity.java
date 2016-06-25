package com.example.administrator.expert_day01_chabaike.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.spfUtils.Pref_Utils;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends Activity {
    private ViewPager welcome_Viewpager;
    private LinearLayout welcome_ll;//点的布局
    private int[]welcome_imgId=new int[]{R.mipmap.slide1,R.mipmap.slide2,R.mipmap.slide3};
    private ImageView imageView;
    private List<ImageView>imageViewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        intiView();
    }

    private void intiView() {
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        welcome_Viewpager= (ViewPager) findViewById(R.id.welcome_viewpager);
        welcome_ll= (LinearLayout) findViewById(R.id.welcome_ll);
        imageViewList=new ArrayList<>();
        for (int i = 0; i <welcome_imgId.length ; i++) {
            imageView=new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(welcome_imgId[i]);
            imageViewList.add(imageView);
            //int current=imageViewList.size()-1;

            imageViewList.get(imageViewList.size()-1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pref_Utils.putBoolean(getApplicationContext(),"isFistRun",false);
                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


        }
        Welcome_vpAdapter vpAdapter=new Welcome_vpAdapter();
        welcome_Viewpager.setAdapter(vpAdapter);
        welcome_Viewpager.addOnPageChangeListener(vpAdapter);

    }
        class Welcome_vpAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{

            @Override
            public int getCount() {
                return imageViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViewList.get(position));
                return imageViewList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
                container.removeView(imageViewList.get(position));
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        }
}
