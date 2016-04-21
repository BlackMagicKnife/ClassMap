package com.example.chenhz.classroommap.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chenhz.classroommap.domain.User;

public class UserService {
	private DatabaseHelper dbHelper;
	public UserService(Context context){
		dbHelper=new DatabaseHelper(context);
	}

	//登录用
	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}
		return false;
	}
	//注册用
	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password,Uday) values(?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),0};
		sdb.execSQL(sql, obj);
		return true;
	}
	//修改密码用
	public boolean modify(String username,String password){
		SQLiteDatabase sdb=dbHelper.getWritableDatabase();
		String sql="update user set password=? where username=?";
		Object obj[]={password,username};
		sdb.execSQL(sql, obj);
		sdb.close();
		return true;
	}
}
