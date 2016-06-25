package com.example.administrator.expert_day01_chabaike.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.expert_day01_chabaike.R;
import com.example.administrator.expert_day01_chabaike.SqlUtils.DBUtils;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class CollectOrRecordFragment extends Fragment{
    private ListView crf_listview;
    private SimpleCursorAdapter adapter;
    private String sqlName;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.collectorrecordfragment,null);
        crf_listview= (ListView) view.findViewById(R.id.cf_listview);
        sqlName=getArguments().getString("sqlName");
        initData();
        return view;
    }

    private void initData() {
        DBUtils dbUtils=new DBUtils(getContext());
        Cursor cursor=dbUtils.queryAll(sqlName);
        System.out.println("查询数据库");
        adapter=new SimpleCursorAdapter(getContext(),
                R.layout.collect_item,
                cursor,
                new String[]{"url","title"},
                new int[]{R.id.collect_title,R.id.collect_url},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        System.out.println("adapter.getCount()"+adapter.getCount());
        crf_listview.setAdapter(adapter);

    }
}
