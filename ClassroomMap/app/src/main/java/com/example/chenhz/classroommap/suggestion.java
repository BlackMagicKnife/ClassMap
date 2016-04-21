package com.example.chenhz.classroommap;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chenhz.classroommap.service.DatabaseHelper;
import com.jay.fragmentdemo.R;

public class suggestion extends Activity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Bundle bundle = this.getIntent().getExtras();
        final String username = bundle.getString("username");
        editText = (EditText)findViewById(R.id.edit);
        Button btn = (Button)findViewById(R.id.btnsuggestion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                DatabaseHelper dbHelper = new DatabaseHelper(suggestion.this);
                SQLiteDatabase sdb=dbHelper.getReadableDatabase();
                String sql="insert into suggestion(content) values(?)";
                Object obj[]={text};
                sdb.execSQL(sql, obj);
                Toast.makeText(suggestion.this, "谢谢您的反馈！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(suggestion.this,MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}
