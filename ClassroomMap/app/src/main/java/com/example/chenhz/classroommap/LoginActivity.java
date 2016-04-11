package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jay.fragmentdemo.R;

public class LoginActivity extends Activity {
    private EditText textname = null;
    private EditText textpasssword = null;
    private Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textname = (EditText)findViewById(R.id.name);
        textpasssword = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginIsSuccess())
                {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean loginIsSuccess(){
        String username = textname.getText().toString();
        String password = textpasssword.getText().toString();
        if(username.equals("")){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.equals("")){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
