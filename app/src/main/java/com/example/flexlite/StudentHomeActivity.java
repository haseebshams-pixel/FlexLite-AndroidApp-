package com.example.flexlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.stud_drawer);
        navigationView = (NavigationView) findViewById(R.id.stud_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)headerView.findViewById(R.id.header_user_name);
        TextView navEmail=(TextView)headerView.findViewById(R.id.header_email);
        navUsername.setText("Haseeb Shams");
        navEmail.setText("hadishams38.hs@gmail.com");

        navigationDrawer();


    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_stud_home);

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
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_reg:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(StudentHomeActivity.this,RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                finish();
                break;
        }
        return true;
    }
}