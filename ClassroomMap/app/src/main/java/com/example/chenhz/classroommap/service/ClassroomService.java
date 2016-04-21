package com.example.chenhz.classroommap.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ClassroomService {
	private DatabaseHelper dbHelper;
	private StringBuilder sb;
	public ClassroomService(Context context){
		dbHelper=new DatabaseHelper(context);
	}

	public int BuildingPnum(String building) {
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		int sum=0;
		String sql="select personNum from classroom where building =?";
		Log.i("building:",building);
		Cursor cursor=sdb.rawQuery(sql, new String[]{building});
		if(cursor.moveToFirst()==true){
			do {
				int personNum = cursor.getInt(cursor.getColumnIndex("personNum"));
				sum+=personNum;
			} while (cursor.moveToNext());
		}
		cursor.close();
        return sum;
    }

	//登录用
	public  String[] search(String day,String time,String building){
		int i=0;
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String[] result = new String[200];
		for(int j=0;j<result.length;j++){
			result[j]="0";
		}
		String sql="select CRnum,personNum from classroom where CRnum not in (select Croom from Course where Cday=? and Ctime=? and Cbuilding=?) and building=? order by personNum";
		Cursor cursor=sdb.rawQuery(sql, new String[]{day,time,building,building});
		if(cursor.moveToFirst()==true){
			do {
				String CRnum = cursor.getString(cursor.getColumnIndex("CRnum"));
				result[i++] = CRnum;
				Log.i("result",CRnum);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return result;
	}

	public int[] roomPnum(String day,String time,String building){
		int i=0;
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		int [] pnum = new int[200];
		String sql="select CRnum,personNum from classroom where CRnum not in (select Croom from Course where Cday=? and Ctime=? and Cbuilding=?) and building=? order by personNum";
		Cursor cursor=sdb.rawQuery(sql, new String[]{day,time,building,building});
		if(cursor.moveToFirst()==true){
			do {
				int Pnum = cursor.getInt(cursor.getColumnIndex("personNum"));
				pnum[i++] = Pnum;
			} while (cursor.moveToNext());
		}
		cursor.close();
		return pnum;
	}
}
