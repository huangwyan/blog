package com.example.administrator.expert_day01_chabaike.SqlUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtils {
	private MySqlHelper helper;

	public DBUtils(Context context) {
		super();
		helper = new MySqlHelper(context);
	}
	// 插入操作
	public long insert(String sqlName,ContentValues values) {
		SQLiteDatabase db = helper.getWritableDatabase();
		long id=db.insert(sqlName, null, values);
		System.out.println("数据库工具类插入方法里面的id"+id);
		return id;
	}
	// 删除操作
	public int delete(String sqlName,String whereClause, String[] whereArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.delete(sqlName, whereClause, whereArgs);
	}
	// 更改操作
	public int update(String sqlName,ContentValues values, String whereClause, String[] whereArgs) {
		SQLiteDatabase db = helper.getWritableDatabase();
		return db.update(sqlName, values, whereClause, whereArgs);
	}
	// 查询所有
	public Cursor queryAll(String sqlName) {
		SQLiteDatabase db = helper.getReadableDatabase();
		return db.query(sqlName, null, null, null, null, null, null);
	}
	public Cursor queryField(String sqlName,String[] columns) {
		SQLiteDatabase db = helper.getReadableDatabase();
		return db.query(sqlName, columns, null, null, null, null, null);
	}
	// 按记录查询
	public Cursor queryCondition(String sqlName,String selection, String[] selectionArgs) {
		SQLiteDatabase db = helper.getReadableDatabase();
		return db.query(sqlName, null, selection, selectionArgs, null, null, null);
	}
}
