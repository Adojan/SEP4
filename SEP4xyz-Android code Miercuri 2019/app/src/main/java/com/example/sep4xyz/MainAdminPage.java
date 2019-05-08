package com.example.sep4xyz;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdminPage extends AppCompatActivity {

    Button createUser;
    Button viewData;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_page);
        createUser = (Button) findViewById(R.id.createUser);
        viewData = (Button) findViewById(R.id.viewData);


    }

    // create user  intent
    public void createUser(View view) {
        Intent createUser = new Intent(MainAdminPage.this, MainCreateUsers.class);
        startActivity(createUser);
    }

    // view data intent
    public void viewData(View view) {
        Intent viewData = new Intent(MainAdminPage.this, SelectBlock.class);
        startActivity(viewData);
    }

    // floating button create user intent
    public void floatCreateUser(View view) {
        Intent createUser = new Intent(MainAdminPage.this, MainCreateUsers.class);
        startActivity(createUser);
    }

}
