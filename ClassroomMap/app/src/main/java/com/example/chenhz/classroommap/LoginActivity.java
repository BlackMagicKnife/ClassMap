package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenhz.classroommap.service.UserService;
import com.jay.fragmentdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends Activity {
    private EditText textname = null;
    private EditText textpasssword = null;
    private Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String DATABASE_PATH = "/data/data/com.example.chenhz.classroommap/databases/";
        String DATABASE_FILENAME = "classroommap.db";

        try {
            String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
            File dir = new File(DATABASE_PATH);
            // 判断SD卡下是否存在存放数据库的目录，如果不存在，新建目录
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                // 如果数据库已经在SD卡的目录下存在，那么不需要重新创建，否则创建文件，并拷贝/res/raw下面的数据库文件
                if (!(new File(databaseFilename)).exists()) {
                    // /res/raw数据库作为输出流
                    InputStream is = LoginActivity.this.getResources().openRawResource(R.raw.classroommap);
                    // 用于存放数据库信息的数据流
                    FileOutputStream fos = new FileOutputStream(databaseFilename);
                    byte[] buffer = new byte[8192];
                    int count = 0;
                    // 把数据写入SD卡目录下
                    while ((count = is.read(buffer)) > 0) {
                        fos.write(buffer, 0, count);
                    }
                    fos.flush();
                    fos.close();
                    is.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }

        textname = (EditText)findViewById(R.id.name);
        textpasssword = (EditText)findViewById(R.id.password);
        button = (Button)findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService uService=new UserService(LoginActivity.this);
                String name = textname.getText().toString();
                String pass = textpasssword.getText().toString();
                if(loginIsSuccess()){
                    if(uService.login(name, pass))
                    {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("username",name);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"登陆失败！用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    }
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
