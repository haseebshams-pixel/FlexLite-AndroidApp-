package com.example.flexlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAttendanceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    EditText edittext;
    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

        drawerLayout = (DrawerLayout) findViewById(R.id.add_attendance_drawer);
        navigationView = (NavigationView) findViewById(R.id.teach_nav);
        menuIcon = (ImageView) findViewById(R.id.stud_menu_icon);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user_name);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText("Farooq Ahmed");
        navEmail.setText("farooq.ahmed@gmail.com");

        navigationDrawer();
        myCalendar = Calendar.getInstance();

        edittext= (EditText) findViewById(R.id.date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }


        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddAttendanceActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_add_attendance);

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

        TableLayout table = (TableLayout) AddAttendanceActivity.this.findViewById(R.id.addAttendacneTable);

        // Inflate your row "template" and fill out the fields.
        TableRow row = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row);
        TableRow row2 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row2.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row2);
        TableRow row3 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row3.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row3);
        TableRow row4 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row4.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row4);
        TableRow row5 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row5.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row5);
        TableRow row6 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row6.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row6);
        TableRow row7 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row7.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row7);
        TableRow row8 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row8.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row8);
        TableRow row9 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row9.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row9);
        TableRow row10 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row10.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row10);
        TableRow row12 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row12.findViewById(R.id.stud_name)).setText("Haseeb Shams");
        table.addView(row12);
        TableRow row13 = (TableRow) LayoutInflater.from(AddAttendanceActivity.this).inflate(R.layout.add_attendance_table_row, null);
        ((TextView) row13.findViewById(R.id.stud_name)).setText("Haseeb Shams");
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
        navigationView.setCheckedItem(R.id.nav_add_attendance);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_add_attendance:
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_add_marks:
                Intent intent = new Intent(AddAttendanceActivity.this,AddMarksActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_home:
                drawerLayout.closeDrawers();
                Intent intent2 = new Intent(AddAttendanceActivity.this,TeacherHomeActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                drawerLayout.closeDrawers();
                Intent intent3 = new Intent(AddAttendanceActivity.this,TeacherLoginActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }
}