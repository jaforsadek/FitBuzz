package com.example.fitbuzz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class login extends AppCompatActivity {


    TextView Register;
    String URL_LOGIN ="http://10.0.2.2/Fitbuzz/login.php";
    private static final String TAG = login.class.getSimpleName();
    EditText user,pass;
    String username ;
    String password;
    Button lgbt;
    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        Register =findViewById(R.id.registertxt);
        user = findViewById(R.id.username2);
        pass = findViewById(R.id.pass);
        lgbt = findViewById(R.id.Loginbtn);

        CheckLogin();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
            }
        });



        progressDialog = new ProgressDialog(login.this);
        progressDialog.setTitle("Login");
        progressDialog.setTitle("Logging into your account");

        lgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.getText().toString().isEmpty()) {

                    Toast.makeText(login.this, "Enter User Name", Toast.LENGTH_SHORT).show();

                } else if (pass.getText().toString().isEmpty()) {

                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {


                    progressDialog.show();
                    Login();

                }
            }
        });

    }


    //-----Data insert function--------//
    private void Login(){

        username = user.getText().toString().trim();
        password = pass.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Login Successful")) {

                    sharedPreferences = getSharedPreferences ("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("uname",username);
                    editor.commit();

                    Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                       user.setText("");
                       pass.setText("");
                       Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();


                params.put("name",username);
                params.put("pass",password);


                progressDialog.dismiss();

                return params;

            }
        };

        AppSingleton.getInstance(this).addToRequestQueue(request,TAG);

    }

    public void CheckLogin() {
        if (sharedPreferences == null)
            sharedPreferences = getSharedPreferences("credentials", MODE_PRIVATE);

        String userName = sharedPreferences.getString("uname", "");

        if (userName != null && !userName.equals("")) {
            Intent i = new Intent(login.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}