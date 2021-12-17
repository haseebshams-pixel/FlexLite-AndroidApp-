package com.example.flexlite.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;

public class ViewMarksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_marks);

        drawerLayout = (DrawerLayout) findViewById(R.id.view_marks_drawer);
        navigationView = (NavigationView) findViewById(R.id.stud_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText("Haseeb Shams");
        navEmail.setText("hadishams38.hs@gmail.com");

        String[] testArray = {"Software Mobile Development", "OOP", "Algorithms"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, testArray);
        AppCompatSpinner mySpinner = (AppCompatSpinner) findViewById(R.id.coursesList);
        mySpinner.setAdapter(spinnerArrayAdapter);
        navigationDrawer();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_marks);

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

        TableLayout table = (TableLayout) ViewMarksActivity.this.findViewById(R.id.ViewMarksTable);

        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow) LayoutInflater.from(ViewMarksActivity.this).inflate(R.layout.three_text_row, null);
        ((TextView) row.findViewById(R.id.txt1)).setText("10");
        ((TextView) row.findViewById(R.id.txt2)).setText("5%");
        ((TextView) row.findViewById(R.id.txt3)).setText("9");
        table.addView(row);
        table.requestLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_marks);
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
                Intent intent2 = new Intent(ViewMarksActivity.this, RegistrationActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_stud_home:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(ViewMarksActivity.this, StudentHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_drop:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(ViewMarksActivity.this, DropCourseActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_withdraw:
                drawerLayout.closeDrawers();
                Intent intent4 = new Intent(ViewMarksActivity.this, WithDrawCourseActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_attendance:
                drawerLayout.closeDrawers();
                Intent intent5 = new Intent(ViewMarksActivity.this, ViewAttendanceActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_marks:
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent6 = new Intent(ViewMarksActivity.this, StudentLoginActivity.class);
                startActivity(intent6);
                break;
        }
        return true;
    }
}