package com.example.flexlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TeacherLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        ImageView backButton = (ImageView) this.findViewById(R.id.back2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, StartActivity.class));
            }
        });
        View signIn = (View) this.findViewById(R.id.signin1);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherLoginActivity.this, TeacherHomeActivity.class));
            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TeacherLoginActivity.this, StartActivity.class));
    }
}