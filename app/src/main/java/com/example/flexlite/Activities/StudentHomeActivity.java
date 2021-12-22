package com.example.flexlite.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flexlite.Fragments.BaseFragment;
import com.example.flexlite.Fragments.attendance;
import com.example.flexlite.Fragments.drop;
import com.example.flexlite.Fragments.marks;
import com.example.flexlite.Fragments.registration;
import com.example.flexlite.Fragments.student_home;
import com.example.flexlite.Fragments.withdraw;
import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Objects;

public class StudentHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    Fragment temp;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.stud_drawer);
        navigationView = (NavigationView) findViewById(R.id.stud_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText("Haseeb Shams");
        navEmail.setText("hadishams38.hs@gmail.com");

        navigationDrawer();
        Log.d("UID", user.getUid());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new student_home()).commit();
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

        switch (item.getItemId()) {
            case R.id.nav_home:
                toolbarTitle.setText("HOME");
                drawerLayout.closeDrawers();
                temp = new student_home();
                break;
            case R.id.nav_reg:
                toolbarTitle.setText("REGISTRATION");
                drawerLayout.closeDrawers();
                temp = new registration();
                break;
            case R.id.nav_drop:
                toolbarTitle.setText("DROP");
                drawerLayout.closeDrawers();
                temp= new drop();
                break;
            case R.id.nav_withdraw:
                toolbarTitle.setText("WITHDRAW");
                drawerLayout.closeDrawers();
                temp = new withdraw();
                break;
            case R.id.nav_attendance:
                toolbarTitle.setText("ATTENDANCE");
                drawerLayout.closeDrawers();
                temp= new attendance();
                break;
            case R.id.nav_marks:
                toolbarTitle.setText("MARKS");
                drawerLayout.closeDrawers();
                temp = new marks();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                FirebaseAuth.getInstance().signOut();
                Intent intent6 = new Intent(StudentHomeActivity.this, StudentLoginActivity.class);
                startActivity(intent6);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, temp).commit();
        return true;
    }
}