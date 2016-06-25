package com.example.administrator.expert_day01_chabaike.SqlUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class MySqlHelper extends SQLiteOpenHelper{
    public static final String SQL_NAME="DDD.db";
    public static final int VERSION=1;
    public MySqlHelper(Context context) {
        super(context, SQL_NAME, null, VERSION);
        //System.out.println("创建数据库成功");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sql = "CREATE TABLE IF NOT EXISTS [collect] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] TEXT(20),[url] TEXT(20));";
        String sql = "CREATE TABLE IF NOT EXISTS [collect] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] TEXT(20),[url] TEXT(50));";
        db.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS [record] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] TEXT(20),[url] TEXT(50));";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
