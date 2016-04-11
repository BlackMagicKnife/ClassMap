package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jay.fragmentdemo.R;

public class StartActivity extends Activity {
    private Button btn1 = null;
    private Button btn2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btn1 = (Button)findViewById(R.id.btn登录);
        btn2 = (Button)findViewById(R.id.注册);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
