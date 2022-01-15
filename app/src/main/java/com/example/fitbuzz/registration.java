package com.example.fitbuzz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {



    private static final String TAG = registration.class.getSimpleName();

    ProgressDialog progressDialog;
    TextView login;
    String  str_name, str_email, str_password;
    String url="http://10.0.2.2/Fitbuzz/registration.php";
    EditText name, email , pass;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        login = findViewById(R.id.logintxt);
        name=findViewById(R.id.username);
        email=findViewById(R.id.email2);
        pass=findViewById(R.id.pass2);
        btn=findViewById(R.id.register);





        //-------Login Activity-----//
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
            }
        });





        progressDialog = new ProgressDialog(registration.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setTitle("We are creating your account");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {

                    Toast.makeText(registration.this, "Enter User Name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {

                    Toast.makeText(registration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {

                    Toast.makeText(registration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {

                    progressDialog.show();
                    insertdata();
                }
            }
        });




    }





    //-----Data insert function--------//
    private void insertdata(){

        str_name = name.getText().toString().trim();
        str_email = email.getText().toString().trim();
        str_password = pass.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(registration.this,response, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registration.this,login.class);
                startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(registration.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();


                params.put("name",str_name);
                params.put("email",str_email);
                params.put("pass",str_password);


                progressDialog.dismiss();

                return params;

            }
        };

        AppSingleton.getInstance(this).addToRequestQueue(request,TAG);

    }




}