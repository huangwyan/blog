package com.example.administrator.expert_day01_chabaike.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.expert_day01_chabaike.Activity.DetailsActivity;
import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.adapter.Content_Adapter;
import com.example.administrator.expert_day01_chabaike.beans.Info;
import com.example.administrator.httplibs.HttpHelper;
import com.example.administrator.httplibs.Request;
import com.example.administrator.httplibs.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by wukai on 16/6/21.
 */
public class ContentFragment extends Fragment {


    private ListView mLv;

    private int class_Id;
    private Content_Adapter content_adapter;
    private List<Info> infos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.contentfragment,null);
        initView(view);
        class_Id =getArguments().getInt("id");
        if (bundle != null){
            Parcelable[] ps =  bundle.getParcelableArray("cache");
            Info[] ins = (Info[]) bundle.getParcelableArray("cache");
            if (ins != null && ins.length != 0){
                infos = Arrays.asList(ins);
                content_adapter = new Content_Adapter(infos);
                mLv.setAdapter(content_adapter);
            }else {
                getDataFromNetwork();
            }
        }else {
            getDataFromNetwork();
        }

        return view;
    }

    private void initView(View view){
        mLv = (ListView) view.findViewById(R.id.cf_listview);
        final Intent intent=new Intent();
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info info= (Info) content_adapter.getItem(position);
                long item_id=info.getId();
                String title=info.getTitle();
                System.out.println(info.getTitle());
                intent.putExtra("id",item_id);
                intent.putExtra("title",title);
                intent.setClass(getActivity(), DetailsActivity.class);
                startActivity(intent);
            }
        });
    }



    private void getDataFromNetwork(){
        String url = "http://www.tngou.net/api/top/list?id="+class_Id;

        StringRequest req = new StringRequest(url, Request.Method.GET, new Request.Callback<String>() {
            @Override
            public void onEror(Exception e) {

            }

            @Override
            public void onResonse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    List<Info> listinfo = parseJson2List(jsonObject);
                    if (listinfo != null){
                        infos.clear();
                        infos.addAll(listinfo);

                        if (content_adapter == null){
                            content_adapter = new Content_Adapter(infos);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mLv.setAdapter(content_adapter);
                                }
                            });
                        }else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    content_adapter.notifyDataSetChanged();
                                }
                            });

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });

        HttpHelper.addRequest(req);
    }


    private List<Info> parseJson2List(JSONObject jsonObject) throws JSONException {

        if (jsonObject == null)return  null;
        JSONArray array = jsonObject.getJSONArray("tngou");
        if (array== null ||array.length() ==0)return null;

        List<Info> list = new ArrayList<>();
        int len = array.length();
        JSONObject obj = null;
        Info info =null;
        for (int i = 0; i <len ; i++) {
            obj = array.getJSONObject(i);
            info = new Info();
            info.setDescription(obj.optString("description"));
            info.setRcount(obj.optInt("rcount"));
            long time = obj.getLong("time");
            String str = new SimpleDateFormat("yyyyMMdd:hhmmss").format(time);
            info.setTime(str);
            info.setImg(obj.optString("img"));
            info.setId(obj.optInt("id"));
            info.setTitle(obj.optString("title"));
            list.add(info);
        }

        return list;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (infos == null || infos.size() == 0) return;
        Info[] parce = new Info[infos.size()];
        infos.toArray(parce);
        outState.putParcelableArray("cache", parce);
    }

}
