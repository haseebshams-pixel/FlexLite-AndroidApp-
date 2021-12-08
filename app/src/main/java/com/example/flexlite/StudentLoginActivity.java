package com.example.flexlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        ImageView backButton = (ImageView) this.findViewById(R.id.back1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, StartActivity.class));

            }
        });
        View signIn = (View) this.findViewById(R.id.signin1);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, StudentHomeActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(StudentLoginActivity.this, StartActivity.class));
    }
}