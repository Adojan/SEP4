package com.example.semester_project_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button logInButton;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         email=findViewById(R.id.emailTextField);
         password=findViewById(R.id.passwordTextField);
         logInButton=findViewById(R.id.logInButton);
         register=findViewById(R.id.registerTextField);


    }
    public void goToBuildingViewActivity(View view)
    {
        Intent intent=new Intent(this, BuildingView.class);
        startActivity(intent);
    }
    public void goToRegisterViewActivity(View view)
    {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
