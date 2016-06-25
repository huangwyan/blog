package com.example.administrator.expert_day01_chabaike.adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.beans.Info;
import com.example.administrator.httplibs.BitmapRequest;
import com.example.administrator.httplibs.HttpHelper;
import com.example.administrator.httplibs.Request;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public class Content_Adapter extends BaseAdapter {
    private List<Info> infoList;

    public Content_Adapter(List<Info> infoList) {
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int position) {
        return infoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        ViewHolder vh;
        if (cv == null){
            cv=View.inflate(parent.getContext(), R.layout.cf_item,null);
            vh = new ViewHolder();
            vh.iv_icon = (ImageView) cv.findViewById(R.id.item_iv);
            vh.tv_desc = (TextView) cv.findViewById(R.id.item_tv_desc);
            vh.tv_rcount =(TextView)cv.findViewById(R.id.item_tv_rc);
            vh.tv_time =(TextView)cv.findViewById(R.id.item_tv_time);
            cv.setTag(vh);

        }

        Info info = infoList.get(position);
        vh = (ViewHolder) cv.getTag();


        vh.tv_time.setText(info.getTime());
        vh.tv_desc.setText(info.getDescription());
        vh.tv_rcount.setText(""+info.getRcount());
        vh.iv_icon.setImageResource(R.mipmap.ic_launcher);
        ///top/160620/0040c25502219f97f3a4d97bfa773cd1.jpg
        loadImage(vh.iv_icon,"http://tnfs.tngou.net/image"+info.getImg()+"_100x100");
        return cv;
    }


    public class ViewHolder{

        public TextView tv_desc;
        public TextView tv_time;
        public TextView tv_rcount;

        public ImageView iv_icon;

    }
    public void loadImage(final ImageView iv, final String url){



        iv.setTag(url);
        BitmapRequest br = new BitmapRequest(url, Request.Method.GET, new Request.Callback< Bitmap>(){
            @Override
            public void onEror(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResonse(final Bitmap response) {
                if (iv != null && response != null && ((String)iv.getTag()).equals(url)){

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(response);
                        }
                    });

                }
            }
        } );
        HttpHelper.addRequest(br);
    }
}
