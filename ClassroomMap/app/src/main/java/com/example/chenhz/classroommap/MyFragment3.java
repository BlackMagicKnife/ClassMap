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
import android.widget.Toast;

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
    private String[] mName = { "  消息通知", "  设置常去教学楼","  使用说明", "  退出登录"};
    private String[] mNum = { "", "", "","" };
    private ArrayList<Map<String, Object>> mData1 = new ArrayList<Map<String, Object>>();
    private ArrayList<Map<String, Object>> mData2 = new ArrayList<Map<String, Object>>();

    public MyFragment3(String content) {
        this.content = content;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fg_content3, container, false);
        list1 = (ListView)view.findViewById(R.id.MyListView_my1);
        list2 = (ListView)view.findViewById(R.id.MyListView_my2);
        Map<String,Object> item1 = new HashMap<String,Object>();
        item1.put("name",mName[0]);
        item1.put("num",mNum[0]);
        mData1.add(item1);
        for (int i=1;i<mNum.length;i++){
            Map<String,Object> item2 = new HashMap<String,Object>();
            item2.put("name",mName[i]);
            item2.put("num",mNum[i]);
            mData2.add(item2);
        }
        SimpleAdapter adapter1 = new SimpleAdapter(getActivity(),mData1,R.layout.item_setting2,new String[]{"name","num"},new int[]{R.id.ItemTitle,R.id.ItemText});
        SimpleAdapter adapter2 = new SimpleAdapter(getActivity(),mData2,R.layout.item_setting1,new String[]{"name","num"},new int[]{R.id.ItemTitle,R.id.ItemText});
        list1.setAdapter(adapter1);
        list2.setAdapter(adapter2);
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    final String[] building = new String[]{"信教", "研教", "中教", "三教"};
                    alert = null;
                    builder = new AlertDialog.Builder(getActivity());
                    alert = builder.setIcon(R.drawable.xinjiao)
                            .setTitle("选择你常去的教学楼")
                            .setSingleChoiceItems(building, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(), "你选择了" + building[which], Toast.LENGTH_SHORT).show();
                                }
                            }).create();
                    alert.show();
                }
                if (position == 1) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("使用说明")
                            .setMessage("选择教学楼（深色表示拥挤，浅色表示空闲）->选择时间->查看查询结果")
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialoginterface, int i){
                                        }
                                    }).show();
                }
                if (position == 2) {
                    Intent intent = new Intent(getActivity(), StartActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}
