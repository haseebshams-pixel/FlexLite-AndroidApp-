package com.example.flexlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        View myview1 =(View) findViewById(R.id.view);
        myview1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(StartActivity.this, TeacherLoginActivity.class));
            }
        });
        View myview =(View) findViewById(R.id.view2);
        myview.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(StartActivity.this, StudentLoginActivity.class));
            }
        });
    }
}