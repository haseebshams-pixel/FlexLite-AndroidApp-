package com.example.flexlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TeacherHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.teach_drawer);
        navigationView = (NavigationView) findViewById(R.id.teach_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)headerView.findViewById(R.id.header_user_name);
        TextView navEmail=(TextView)headerView.findViewById(R.id.header_email);
        navUsername.setText("Farooq Ahmed");
        navEmail.setText("farooq.ahmed@gmail.com");

        navigationDrawer();
    }
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_attendance:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(TeacherHomeActivity.this,AddAttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_add_marks:
                drawerLayout.closeDrawers();
                Intent intent2 = new Intent(TeacherHomeActivity.this,AddMarksActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_home:
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(TeacherHomeActivity.this,TeacherLoginActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}