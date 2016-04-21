package com.example.chenhz.classroommap;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chenhz.classroommap.service.ClassroomService;
import com.jay.fragmentdemo.R;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {

    private String content;
    public MyFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fg_content, container, false);

        ClassroomService classroomService = new ClassroomService(getActivity());
        int xinjiao = classroomService.BuildingPnum("信教");
        int zhongjiao = classroomService.BuildingPnum("中教");
        int yanjiao = classroomService.BuildingPnum("研教");
        int sanjiao = classroomService.BuildingPnum("三教");

        Button btn1 = (Button) view.findViewById(R.id.信教);
        if(xinjiao>1000){
            btn1.setBackgroundResource(R.drawable.buttondeepblue);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("Building","信教");
                startActivity(intent);
            }
        });

        Button btn2 = (Button) view.findViewById(R.id.中教);
        if(zhongjiao>1000){
            btn2.setBackgroundResource(R.drawable.buttondeepblue);
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("Building", "中教");
                startActivity(intent);
            }
        });

        Button btn3 = (Button) view.findViewById(R.id.研教);
        if(yanjiao>1000){
            btn3.setBackgroundResource(R.drawable.buttondeepblue);
        }
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("Building", "研教");
                startActivity(intent);
            }
        });

        Button btn4 = (Button) view.findViewById(R.id.三教);
        if(sanjiao>1000){
            btn4.setBackgroundResource(R.drawable.buttondeepblue);
        }
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("Building", "三教");
                startActivity(intent);
            }
        });
        return view;
    }

}
