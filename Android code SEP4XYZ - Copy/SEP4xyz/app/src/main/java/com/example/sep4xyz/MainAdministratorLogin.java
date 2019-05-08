package com.example.sep4xyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainAdministratorLogin extends AppCompatActivity {


    EditText userName;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_administrator_login);
        userName=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);





    }
    // Method used to autentificate the admin account
    public void login (View view){
        String getUserName= userName.getText().toString();
        String getPassword= password.getText().toString();
            if(getUserName.equalsIgnoreCase("admin@gmail.com")&&(getPassword.equals("admin"))){
                Intent logAdmin= new Intent(MainAdministratorLogin.this,MainAdminPage.class);
                startActivity(logAdmin);

        } else {
                Toast.makeText(MainAdministratorLogin.this," Invalid user name or password",Toast.LENGTH_SHORT).show();
            }
    }
}
