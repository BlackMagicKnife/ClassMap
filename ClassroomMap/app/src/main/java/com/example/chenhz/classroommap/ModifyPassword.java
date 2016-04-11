package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jay.fragmentdemo.R;

public class ModifyPassword extends Activity {
    private Button btn =  null;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        mContext = ModifyPassword.this;
        btn = (Button)findViewById(R.id.btnModify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "修改成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyPassword.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
