package com.example.flexlite.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flexlite.Fragments.BaseFragment;
import com.example.flexlite.Fragments.addAttendance;
import com.example.flexlite.Fragments.addMarks;
import com.example.flexlite.Fragments.student_home;
import com.example.flexlite.Fragments.teacher_home;
import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    Fragment temp;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.teach_drawer);
        navigationView = (NavigationView) findViewById(R.id.teach_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)headerView.findViewById(R.id.header_user_name);
        TextView navEmail=(TextView)headerView.findViewById(R.id.header_email);
        navUsername.setText("Farooq Ahmed");
        navEmail.setText("farooq.ahmed@gmail.com");

        navigationDrawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new teacher_home()).commit();
        toolbarTitle.setText("HOME");
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
        List fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;
        for (Object f : fragmentList) {
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPressed();

                if (handled) {
                    break;
                }
            }
        }

        if (!handled) {
            super.onBackPressed();
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
                toolbarTitle.setText("ATTENDANCE");
                drawerLayout.closeDrawers();
                temp = new addAttendance();
                break;
            case R.id.nav_add_marks:
                toolbarTitle.setText("MARKS");
                drawerLayout.closeDrawers();
                temp = new addMarks();
                break;
            case R.id.nav_home:
                toolbarTitle.setText("HOME");
                drawerLayout.closeDrawers();
                temp = new teacher_home();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(TeacherHomeActivity.this,TeacherLoginActivity.class);
                startActivity(intent3);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
        return true;
    }
}