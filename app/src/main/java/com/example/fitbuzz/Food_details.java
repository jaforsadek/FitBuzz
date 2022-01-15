package com.example.fitbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Food_details extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);


    textView = findViewById(R.id.txt);
    String dstory = getIntent().getStringExtra("story");
    textView.setText(dstory);


    }

    public void goback(View view) {

        Intent intent = new Intent(Food_details.this,Nutrition.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Food_details.this,Nutrition.class);
        startActivity(intent);
        finish();
    }


}