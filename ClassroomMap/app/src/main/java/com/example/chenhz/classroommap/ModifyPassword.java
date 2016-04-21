package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenhz.classroommap.service.UserService;
import com.jay.fragmentdemo.R;

public class ModifyPassword extends Activity {
    private EditText textname = null;
    private EditText textpasssword1 = null;
    private EditText textpasssword2 = null;
    private Button btn =  null;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        Bundle bundle = this.getIntent().getExtras();
        final String username = bundle.getString("username");
        TextView t = (TextView)findViewById(R.id.username);
        t.setText(username);
        textpasssword1 = (EditText)findViewById(R.id.newpassword1);
        textpasssword2 = (EditText)findViewById(R.id.newpassword2);
        mContext = ModifyPassword.this;
        btn = (Button)findViewById(R.id.btnModify);
        btn.setOnClickListener(new View.OnClickListener() {
            UserService uService=new UserService(ModifyPassword.this);
            @Override
            public void onClick(View v) {
                String pass = textpasssword1.getText().toString();
                if(ModifyIsSuccess()){
                    uService.modify(username,pass);
                    Toast.makeText(mContext, "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ModifyPassword.this,MainActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean ModifyIsSuccess(){
        String password1 = textpasssword1.getText().toString();
        String password2 = textpasssword2.getText().toString();
        if(password1.length()<6){
            Toast.makeText(ModifyPassword.this,"密码长度必须大于6位！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password1.equals(password2)){
            Toast.makeText(ModifyPassword.this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
