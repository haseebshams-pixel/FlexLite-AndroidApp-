package com.example.flexlite.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView)headerView.findViewById(R.id.header_user_name);
        TextView navEmail=(TextView)headerView.findViewById(R.id.header_email);
        navUsername.setText("Haseeb Shams");
        navEmail.setText("hadishams38.hs@gmail.com");

        navigationDrawer();
        Log.d("UID",user.getUid());


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
            case R.id.nav_drop:
                drawerLayout.closeDrawers();
                Intent intent2 = new Intent(StudentHomeActivity.this,DropCourseActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_withdraw:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(StudentHomeActivity.this,WithDrawCourseActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_attendance:
                drawerLayout.closeDrawers();
                Intent intent4 = new Intent(StudentHomeActivity.this,ViewAttendanceActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_marks:
                drawerLayout.closeDrawers();
                Intent intent5 = new Intent(StudentHomeActivity.this,ViewMarksActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                FirebaseAuth.getInstance().signOut();
                Intent intent6 = new Intent(StudentHomeActivity.this,StudentLoginActivity.class);
                startActivity(intent6);
                break;
        }
        return true;
    }
}