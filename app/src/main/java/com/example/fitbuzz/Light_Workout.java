package com.example.fitbuzz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Light_Workout extends AppCompatActivity {


    int[] newarray;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor shpEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_workout);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.getOverflowIcon().setColorFilter(Color.WHITE , PorterDuff.Mode.SRC_ATOP);
        CheckLogin();

        newarray = new int[]{

                R.id.pose1, R.id.pose2, R.id.pose3, R.id.pose4, R.id.pose5, R.id.pose6, R.id.pose7, R.id.pose8, R.id.pose9, R.id.pose10,
                R.id.pose11, R.id.pose12, R.id.pose13, R.id.pose14,R.id.pose15,


        };

    }

    public void Imagebtnclick(View view) {


        for (int i = 0; i < newarray.length; i++) {

            if (view.getId() == newarray[i]){


                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(Light_Workout.this,ThirdActivity.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }


        }
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
                Toast.makeText(Light_Workout.this, "Privacy and Policy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.terms:
                Toast.makeText(Light_Workout.this, "Terms and Conditions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rate:
                Toast.makeText(Light_Workout.this, "Rate us on google play store", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;
    }




    ///---------login check---------///
    public void CheckLogin() {
        if (sharedPreferences == null)
            sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

        String userName = sharedPreferences.getString("uname", "");

        if (userName == null && userName.equals("")) {
            Intent i = new Intent(Light_Workout.this, login.class);
            startActivity(i);
            finish();
        }
    }



    //------Logout------//
    public void Logout() {
        try {
            if (sharedPreferences == null)
                sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

            shpEditor = sharedPreferences.edit();
            shpEditor.putString("uname", "");
            shpEditor.commit();

            Intent i = new Intent(Light_Workout.this, login.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(Light_Workout.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

}
