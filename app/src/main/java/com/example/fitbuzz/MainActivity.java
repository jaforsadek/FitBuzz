package com.example.fitbuzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button L_btn, H_btn;
    TextView  txtInfo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor shpEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L_btn = findViewById(R.id.btn1);
        H_btn = findViewById(R.id.btn2);
        txtInfo = findViewById(R.id.user);

        //--checking login status-----//
        CheckLogin();


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);


        L_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Light_Workout.class);
                startActivity(intent);


            }
        });


        H_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Heavy_Workout.class);
                startActivity(intent);

            }
        });


    }

    public void light(View view) {

        Intent intent = new Intent(MainActivity.this, Light_Workout.class);
        startActivity(intent);

    }

    public void heavy(View view) {


        Intent intent = new Intent(MainActivity.this, Heavy_Workout.class);
        startActivity(intent);
    }

    public void nutri(View view) {

        Intent intent = new Intent(MainActivity.this, Nutrition.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.lgout:
                Logout();
                break;
            case R.id.privacy:
                Toast.makeText(MainActivity.this, "Privacy and Policy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms:
                Toast.makeText(MainActivity.this, "Terms and Conditions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                Toast.makeText(MainActivity.this, "Rate us on google play store", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;
    }


    public void CheckLogin() {
        if (sharedPreferences == null)
            sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

        String userName = sharedPreferences.getString("uname", "");

        if (userName != null && !userName.equals("")) {
            txtInfo.setText("Welcome " + userName);

        } else
        {
            Intent i = new Intent(MainActivity.this, login.class);
            startActivity(i);
            finish();
        }
    }


    public void Logout() {
        try {
            if (sharedPreferences == null)
                sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

            shpEditor = sharedPreferences.edit();
            shpEditor.putString("uname", "");
            shpEditor.commit();

            Intent i = new Intent(MainActivity.this, login.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }


}