package com.example.chenhz.classroommap.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

/**
 * Created by Chen Hz on 2016/4/20.
 */
public class Sign {
    private DatabaseHelper dbHelper;
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    String out = String.format("%04d-%02d-%02d", year, month, day);
    public Sign(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean HasSigned(String username){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        cursor.moveToFirst();
        String date = cursor.getString(cursor.getColumnIndex("signdate"));
        cursor.close();
        if(date.equals(out)){
            return true;
        }
        else{
            int days = getDays(username);
            String sql1="update user set signdate=?,Uday=? where username=?";
            Object obj[]={out,days+1,username};
            sdb.execSQL(sql1, obj);
            return false;
        }
    }
    public int getDays(String username){
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        String sql="select * from user where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        cursor.moveToFirst();
        int days = cursor.getInt(cursor.getColumnIndex("Uday"));
        cursor.close();
        return days;
    }
}
