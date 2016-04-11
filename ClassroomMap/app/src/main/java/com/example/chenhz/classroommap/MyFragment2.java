package com.example.chenhz.classroommap;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jay.fragmentdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Hz on 2016/3/29.
 */
@SuppressLint("ValidFragment")
public class MyFragment2 extends Fragment {
    private String content;
    ListView list;
    private String[] mName = { "  修改信息", "  自习打卡", "  意见反馈"};
    private String[] mNum = { " 重置登录密码", " 已登录天数", "" };
    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    public MyFragment2(String content) {
        this.content = content;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fg_content2, container, false);
        list = (ListView)view.findViewById(R.id.MyListView_my);
        for (int i=0;i<mNum.length;i++){
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("name",mName[i]);
            item.put("num",mNum[i]);
            mData.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,R.layout.item_setting1,new String[]{"name","num"},new int[]{R.id.ItemTitle,R.id.ItemText});
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), ModifyPassword.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "打卡成功！", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(),suggestion.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
