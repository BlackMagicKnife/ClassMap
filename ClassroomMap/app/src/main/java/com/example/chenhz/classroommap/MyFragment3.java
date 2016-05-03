package com.example.chenhz.classroommap;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.jay.fragmentdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Hz on 2016/3/29.
 */
@SuppressLint("ValidFragment")
public class MyFragment3 extends Fragment {
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private String content;
    ListView list1,list2;
    private String[] mName = { "  使用说明", "  退出登录"};
    private String[] mNum = { "","" };

    private ArrayList<Map<String, Object>> mData2 = new ArrayList<Map<String, Object>>();

    public MyFragment3(String content) {
        this.content = content;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fg_content3, container, false);

        list2 = (ListView)view.findViewById(R.id.MyListView_my2);
        Map<String,Object> item1 = new HashMap<String,Object>();

        for (int i=0;i<mNum.length;i++){
            Map<String,Object> item2 = new HashMap<String,Object>();
            item2.put("name",mName[i]);
            item2.put("num",mNum[i]);
            mData2.add(item2);
        }

        SimpleAdapter adapter2 = new SimpleAdapter(getActivity(),mData2,R.layout.item_setting1,new String[]{"name","num"},new int[]{R.id.ItemTitle,R.id.ItemText});

        list2.setAdapter(adapter2);
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("使用说明")
                            .setMessage("选择教学楼（深色表示拥挤，浅色表示空闲）->选择时间->查看查询结果")
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialoginterface, int i){
                                        }
                                    }).show();
                }
                if (position == 1) {
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}
