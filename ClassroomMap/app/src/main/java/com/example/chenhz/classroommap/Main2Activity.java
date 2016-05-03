package com.example.chenhz.classroommap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhz.classroommap.service.ClassroomService;
import com.jay.fragmentdemo.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity{
    private Spinner weekday = null;
    private Spinner time = null;
    String day = "周一";
    String time1 = "1~2节";
    ArrayAdapter<String> weekdayAdapter = null;
    ArrayAdapter<String> timeAdapter = null;
    static int weekdayPosition = 4;

    private ExpandableListView expandableListView;
    private List<String> group_list;

    private List<String> item_lt1;
    private List<String> item_lt2;
    private List<String> item_lt3;
    private List<String> item_lt4;
    private List<String> item_lt5;
    private List<String> item_lt6;

    private List<List<String>> item_list;

    private List<List<Integer>> item_list2;

    private MyExpandableListViewAdapter adapter;

    private Context mContext;

    private String[] province = new String[] {"周一","周二","周三","周四","周五"};
    private String[][] city = new String[][]
            {
                    { "1~2节", "3~5节", "6~7节", "8~10节", "11~13节"},
                    { "1~2节", "3~5节", "6~7节", "8~10节", "11~13节"},
                    { "1~2节", "3~5节", "6~7节", "8~10节", "11~13节"},
                    { "1~2节", "3~5节", "6~7节", "8~10节", "11~13节"},
                    { "1~2节", "3~5节", "6~7节", "8~10节", "11~13节"}
            };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setSpinner();
        Bundle bundle = this.getIntent().getExtras();
        final String name = bundle.getString("Building");
        //接收name值
       TextView view = (TextView) findViewById(R.id.building);
        view.setText("您选择了"+name);
        ImageView imgv = (ImageView) findViewById(R.id.buildingimage);
        switch(name){
            case "信教":
                imgv.setImageResource(R.drawable.xinjiao);
                break;
            case "三教":
                imgv.setImageResource(R.drawable.sanjiao);
                break;
            case "中教":
                imgv.setImageResource(R.drawable.zhongjiao);
                break;
            case "研教":
                imgv.setImageResource(R.drawable.yanjiao);
                break;
        }


        //点击查看地图
        Button btnmap = (Button)findViewById(R.id.map);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,MapActivity.class);
                intent.putExtra("building",name);
                startActivity(intent);
            }
        });

        Button btnsearch = (Button)findViewById(R.id.search);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassroomService classroomService = new ClassroomService(Main2Activity.this);
                String[] result = classroomService.search(day, time1, name);
                int[] Pnum = classroomService.roomPnum(day,time1,name);
                item_lt1.clear();
                item_lt2.clear();
                item_lt3.clear();
                item_lt4.clear();
                item_lt5.clear();
                item_lt6.clear();
                int floornum;
                int rPnum;
                if(name.equals("信教")){
                    for (int i = 0; result[i]!="0"; i++)
                    {
                        floornum = Integer.valueOf(result[i]).intValue();
                        rPnum = Pnum[i];
                        if(floornum<2000) {
                            item_lt1.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>2000&&floornum<3000){
                            item_lt2.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>3000&&floornum<4000){
                            item_lt3.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>4000&&floornum<5000){
                            item_lt4.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>5000&&floornum<6000){
                            item_lt5.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>6000){
                            item_lt6.add(result[i]+"     "+rPnum+"人");
                        }
                    }
                }
                else if(name.equals("三教")||name.equals("中教")||name.equals("研教")){
                    for (int i = 0; result[i]!="0"; i++)
                    {
                        floornum = Integer.valueOf(result[i]).intValue();
                        rPnum = Pnum[i];
                        if(floornum<200) {
                            item_lt1.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>200&&floornum<300){
                            item_lt2.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>300&&floornum<400){
                            item_lt3.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>400&&floornum<500){
                            item_lt4.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>500&&floornum<600){
                            item_lt5.add(result[i]+"     "+rPnum+"人");
                        }
                        else if(floornum>600){
                            item_lt6.add(result[i]+"     "+rPnum+"人");
                        }
                    }
                }

                expandableListView.collapseGroup(0);
                expandableListView.collapseGroup(1);
                expandableListView.collapseGroup(2);
                expandableListView.collapseGroup(3);
                expandableListView.collapseGroup(4);
                expandableListView.collapseGroup(5);
            }
        });

        group_list = new ArrayList<String>();
        group_list.add("一楼");
        group_list.add("二楼");
        group_list.add("三楼");
        group_list.add("四楼");
        group_list.add("五楼");
        group_list.add("六楼");

        item_lt1 = new ArrayList<>();

        item_lt2 = new ArrayList<>();

        item_lt3 = new ArrayList<>();

        item_lt4 = new ArrayList<>();

        item_lt5 = new ArrayList<>();

        item_lt6 = new ArrayList<>();

        item_list = new ArrayList<>();
        item_list.add(item_lt1);
        item_list.add(item_lt2);
        item_list.add(item_lt3);
        item_list.add(item_lt4);
        item_list.add(item_lt5);
        item_list.add(item_lt6);

        List<Integer> tmp_list = new ArrayList<Integer>();
        tmp_list.add(R.drawable.ic_launcher);
        tmp_list.add(R.drawable.ic_launcher);
        tmp_list.add(R.drawable.ic_launcher);
        tmp_list.add(R.drawable.ic_launcher);
        tmp_list.add(R.drawable.ic_launcher);
        tmp_list.add(R.drawable.ic_launcher);

        item_list2 = new ArrayList<List<Integer>>();
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);

        expandableListView = (ExpandableListView)findViewById(R.id.expendlist);
        expandableListView.setGroupIndicator(null);

        // 监听组点击
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @SuppressLint("NewApi")
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if (item_list.get(groupPosition).isEmpty())
                {
                    return true;
                }
                return false;
            }
        });

        // 监听每个分组里子控件的点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {

                Toast.makeText(Main2Activity.this, "group=" + groupPosition + "---child=" + childPosition + "---" + item_list.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        adapter = new MyExpandableListViewAdapter(this);

        expandableListView.setAdapter(adapter);

    }

    private void setSpinner()
    {
        weekday = (Spinner)findViewById(R.id.spin_weekday);
        time = (Spinner)findViewById(R.id.spin_time);

        //绑定适配器和值
        weekdayAdapter = new ArrayAdapter<String>(Main2Activity.this,
                android.R.layout.simple_spinner_item, province);
        weekday.setAdapter(weekdayAdapter);
        weekday.setSelection(0, true);  //设置默认选中项，此处为默认选中第0个值

        timeAdapter = new ArrayAdapter<String>(Main2Activity.this,
                android.R.layout.simple_spinner_item, city[3]);
        time.setAdapter(timeAdapter);
        time.setSelection(0, true);  //默认选中第0个


        //省级下拉框监听
        weekday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //position为当前省级选中的值的序号

                //将地级适配器的值改变为city[position]中的值
                timeAdapter = new ArrayAdapter<String>(
                        Main2Activity.this, android.R.layout.simple_spinner_item, city[position]);
                // 设置二级下拉列表的选项内容适配器
                time.setAdapter(timeAdapter);
                weekdayPosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
                day = arg0.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter
    {

        private Context context;

        public MyExpandableListViewAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public int getGroupCount()
        {
            return group_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return item_list.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return group_list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return item_list.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public boolean hasStableIds()
        {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            GroupHolder groupHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expandlist_group, null);
                groupHolder = new GroupHolder();
                groupHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                groupHolder.img = (ImageView)convertView.findViewById(R.id.img);
                convertView.setTag(groupHolder);
            }
            else
            {
                groupHolder = (GroupHolder)convertView.getTag();
            }

            if (!isExpanded)
            {
                groupHolder.img.setBackgroundResource(R.drawable.collapse);
            }
            else
            {
                groupHolder.img.setBackgroundResource(R.drawable.expanded);
            }

            groupHolder.txt.setText(group_list.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
        {
            ItemHolder itemHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expandlist_item, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                itemHolder.img = (ImageView)convertView.findViewById(R.id.img);
                convertView.setTag(itemHolder);
            }
            else
            {
                itemHolder = (ItemHolder)convertView.getTag();
            }
            itemHolder.txt.setText(item_list.get(groupPosition).get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }

    }

    class GroupHolder
    {
        public TextView txt;
        public ImageView img;
    }

    class ItemHolder
    {
        public ImageView img;
        public TextView txt;
    }
}
