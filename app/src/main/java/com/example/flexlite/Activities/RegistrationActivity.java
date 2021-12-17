package com.example.flexlite.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;

public class RegistrationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        drawerLayout = (DrawerLayout) findViewById(R.id.register_drawer);
        navigationView = (NavigationView) findViewById(R.id.stud_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText("Haseeb Shams");
        navEmail.setText("hadishams38.hs@gmail.com");

        navigationDrawer();
    }
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_reg);

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

        TableLayout table = (TableLayout) RegistrationActivity.this.findViewById(R.id.registerTable);

        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row.findViewById(R.id.courseName)).setText("Software Mobile Development");
        TextView btn = (TextView)row.findViewById(R.id.reg_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row);
                //Toast.makeText(RegistrationActivity.this, "Register 1", Toast.LENGTH_SHORT).show();
            }
        });
        table.addView(row);


        TableRow row2 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row2.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn2 = (TextView)row2.findViewById(R.id.reg_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row2);

            }
        });
        table.addView(row2);


        table.requestLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_reg);
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
        switch (item.getItemId()) {
            case R.id.nav_reg:
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_stud_home:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(RegistrationActivity.this,StudentHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_drop:
                drawerLayout.closeDrawers();
                Intent intent2 = new Intent(RegistrationActivity.this,DropCourseActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_withdraw:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(RegistrationActivity.this,WithDrawCourseActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_attendance:
                drawerLayout.closeDrawers();
                Intent intent4 = new Intent(RegistrationActivity.this,ViewAttendanceActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_marks:
                drawerLayout.closeDrawers();
                Intent intent5 = new Intent(RegistrationActivity.this,ViewMarksActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent6 = new Intent(RegistrationActivity.this,StudentLoginActivity.class);
                startActivity(intent6);
                break;
        }
        return true;
    }
}