package com.example.chenhz.classroommap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jay.fragmentdemo.R;

public class MapActivity extends Activity {

    public String building;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Bundle bundle = this.getIntent().getExtras();
        building = bundle.getString("building");
        ImageView map = (ImageView)findViewById(R.id.map);
        if(building.equals("信教")){
            map.setImageResource(R.drawable.xinjiaomap);
        }
        else if(building.equals("研教")){
            map.setImageResource(R.drawable.yanjiaomap);
        }
        else if(building.equals("三教")){
            map.setImageResource(R.drawable.sanjiaomap);
        }
        else if (building.equals("中教")){
            map.setImageResource(R.drawable.zhongjiaomap);
        }
    }

}
