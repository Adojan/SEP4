package com.example.sep4xyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button loginAdministrator;
    Button loginUser;
    TextView textView;
     TextView hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginAdministrator=(Button) findViewById(R.id.loginAdministrator);
        loginUser=(Button) findViewById(R.id.loginUser);


    }
    public void AdminLogin(View view){

            Intent adminLogin= new Intent(this,MainAdministratorLogin.class);
            startActivity(adminLogin);
    }
    public void userLogin(View view){

            Intent userLogin= new Intent(this,logIn.class);
            startActivity(userLogin);
    }



}
