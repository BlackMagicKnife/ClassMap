package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenhz.classroommap.domain.User;
import com.example.chenhz.classroommap.service.UserService;
import com.jay.fragmentdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegisterActivity extends Activity {
    private EditText textname = null;
    private EditText textpasssword1 = null;
    private EditText textpasssword2 = null;
    private Button btn = null;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
                    InputStream is = RegisterActivity.this.getResources().openRawResource(R.raw.classroommap);
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
        textpasssword1 = (EditText)findViewById(R.id.password1);
        textpasssword2 = (EditText)findViewById(R.id.password2);
        mContext = RegisterActivity.this;
        btn = (Button)findViewById(R.id.btnRegiter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService uService=new UserService(RegisterActivity.this);
                String name = textname.getText().toString();
                String pass = textpasssword1.getText().toString();
                if(registerIsSuccess()){
                    User user=new User();
                    user.setUsername(name);
                    user.setPassword(pass);
                    user.setUday(0);
                    uService.register(user);
                    Toast.makeText(mContext, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean registerIsSuccess(){
        String username = textname.getText().toString();
        String password1 = textpasssword1.getText().toString();
        String password2 = textpasssword2.getText().toString();
        if(password1.length()<6){
            Toast.makeText(RegisterActivity.this,"密码长度必须大于6位！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password1.equals(password2)){
            Toast.makeText(RegisterActivity.this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
