package com.example.fitbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenOk extends AppCompatActivity {


    Animation up,down;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreenok);



        ImageView imageView=findViewById(R.id.appsplash);
        up= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up);
        imageView.setAnimation(up);



        TextView textView=findViewById(R.id.appname);
        down=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        textView.setAnimation(down);



        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                startActivity(new Intent(SplashScreenOk.this,login.class));
                finish();

            }
        }, 3500);


    }
}