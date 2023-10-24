package com.adi.loginsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etAlamat;

    private Button btnLogin, btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnlogin);
       btnRegister = findViewById(R.id.btnRegister);

       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String username = etUsername.getText().toString();
               String password = etPassword.getText().toString();

               if (! (username.isEmpty() || password.isEmpty())){

                   RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                   StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlLogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {

                           if (response.equals("Selamat Datang")){
                               Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                               startActivity(new Intent(getApplicationContext(),MainActivity.class));
                           }else {
                               Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();

                           }
                       }
                   }, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                       }
                   });
                   requestQueue.add(stringRequest);
               }else {
                   Toast.makeText(getApplicationContext(), "Password dan Username salah", Toast.LENGTH_SHORT).show();

               }
           }
       });
    }
}