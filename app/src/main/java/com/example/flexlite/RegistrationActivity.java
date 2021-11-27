package com.example.flexlite;

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
import android.widget.Toast;

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

        TableRow row3 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row3.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn3 = (TextView)row2.findViewById(R.id.reg_btn);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row3);

            }
        });
        table.addView(row3);

        TableRow row4 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row4.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn4 = (TextView)row2.findViewById(R.id.reg_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row4);

            }
        });
        table.addView(row4);

        TableRow row5 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row5.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn5 = (TextView)row2.findViewById(R.id.reg_btn);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row5);

            }
        });
        table.addView(row5);

        TableRow row6 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row6.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn6 = (TextView)row2.findViewById(R.id.reg_btn);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row6);

            }
        });
        table.addView(row6);


        TableRow row7 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row7.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn7 = (TextView)row2.findViewById(R.id.reg_btn);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row7);

            }
        });
        table.addView(row7);



        TableRow row8 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row8.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn8 = (TextView)row2.findViewById(R.id.reg_btn);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row8);

            }
        });
        table.addView(row8);


        TableRow row9 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row9.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn9 = (TextView)row2.findViewById(R.id.reg_btn);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row9);

            }
        });
        table.addView(row9);


        TableRow row10 = (TableRow) LayoutInflater.from(RegistrationActivity.this).inflate(R.layout.register_table_row, null);
        ((TextView) row10.findViewById(R.id.courseName)).setText("Software Design Analysis");
        TextView btn10 = (TextView)row2.findViewById(R.id.reg_btn);
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.removeView(row10);

            }
        });
        table.addView(row10);


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
//            case R.id.nav_logout:
//                finish();
//                break;
        }
        return true;
    }
}