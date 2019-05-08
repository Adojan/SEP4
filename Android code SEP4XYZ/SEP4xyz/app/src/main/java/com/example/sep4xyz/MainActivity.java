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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginAdministrator=(Button) findViewById(R.id.loginAdministrator);
        loginUser=(Button) findViewById(R.id.loginUser);


    }
    public void AdminLogin(View view){
        if (view==loginAdministrator){
            Intent adminLogin= new Intent(MainActivity.this,MainAdministratorLogin.class);
            startActivity(adminLogin);
            finish();
        }

    }
    public void userLogin(View view){
        if (view==loginUser){
            Intent userLogin= new Intent(MainActivity.this,logIn.class);
            startActivity(userLogin);
            finish();
        }

    }



}
