package com.example.flexlite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flexlite.R;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
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
    @Override
    public void onBackPressed() {
       
    }
}