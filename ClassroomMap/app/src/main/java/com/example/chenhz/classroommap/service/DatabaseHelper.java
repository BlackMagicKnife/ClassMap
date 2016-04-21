package com.example.chenhz.classroommap.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static String name="classroomMap.db";
	static int dbVersion=1;
	public DatabaseHelper(Context context) {
		super(context, name, null, dbVersion);
	}
	public void onCreate(SQLiteDatabase db) {
		String sql1="create table user(username varchar(20),password varchar(20),Uday integer)";
		String sql2="create table Course(Cid integer primary key autoincrement,Cname varchar(30),Cday varchar(10),Ctime varchar(20),Cbuilding varchar(20),Croom int)";
		String sql3="create table classroom(CRnum integer primary key autoincrement,building varchar(20),personNum integer)";
		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
