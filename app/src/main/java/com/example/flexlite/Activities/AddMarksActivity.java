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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flexlite.R;
import com.google.android.material.navigation.NavigationView;

public class AddMarksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);

        drawerLayout = (DrawerLayout) findViewById(R.id.add_marks_drawer);
        navigationView = (NavigationView) findViewById(R.id.teach_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText("Farooq Ahmed");
        navEmail.setText("farooq.ahmed@gmail.com");

        navigationDrawer();
    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_add_marks);

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

        TableLayout table = (TableLayout) AddMarksActivity.this.findViewById(R.id.addmarkstable);

        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row.findViewById(R.id.std_marks)).setText("10");
        table.addView(row);
        TableRow row2 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row2.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row2.findViewById(R.id.std_marks)).setText("10");
        table.addView(row2);
        TableRow row3 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row3.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row3.findViewById(R.id.std_marks)).setText("10");
        table.addView(row3);
        TableRow row4 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row4.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row4.findViewById(R.id.std_marks)).setText("10");
        table.addView(row4);
        TableRow row5 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row5.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row5.findViewById(R.id.std_marks)).setText("10");
        table.addView(row5);
        TableRow row6 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row6.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row6.findViewById(R.id.std_marks)).setText("10");
        table.addView(row6);
        TableRow row7 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row7.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row7.findViewById(R.id.std_marks)).setText("10");
        table.addView(row7);
        TableRow row8 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row8.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row8.findViewById(R.id.std_marks)).setText("10");
        table.addView(row8);
        TableRow row9 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row9.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row9.findViewById(R.id.std_marks)).setText("10");
        table.addView(row9);
        TableRow row11 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row11.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row11.findViewById(R.id.std_marks)).setText("10");
        table.addView(row11);
        TableRow row12 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row12.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row12.findViewById(R.id.std_marks)).setText("10");
        table.addView(row12);
        TableRow row13 = (TableRow) LayoutInflater.from(AddMarksActivity.this).inflate(R.layout.add_marks_table_row, null);
        ((TextView) row13.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        ((EditText) row13.findViewById(R.id.std_marks)).setText("10");
        table.addView(row13);


        table.requestLayout();

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
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_add_marks);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_add_attendance:
                Intent intent = new Intent(AddMarksActivity.this,AddAttendanceActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_add_marks:
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_home:
                Intent intent2 = new Intent(AddMarksActivity.this,TeacherHomeActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(AddMarksActivity.this,TeacherLoginActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}