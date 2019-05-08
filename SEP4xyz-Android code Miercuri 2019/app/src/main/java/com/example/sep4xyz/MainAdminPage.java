package com.example.sep4xyz;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdminPage extends AppCompatActivity implements View.OnClickListener {

    private Button createUser;
    private Button viewData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_page);
        createUser = findViewById(R.id.createUser);
        viewData = findViewById(R.id.viewData);
        createUser.setOnClickListener(this);
        viewData.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == createUser) {
            Intent createUser = new Intent(MainAdminPage.this, MainCreateUsers.class);
            startActivity(createUser);
        }
        if (view == viewData) {
            Intent viewData = new Intent(MainAdminPage.this, SelectBlock.class);
            startActivity(viewData);
        }
    }

    // floating button create user intent
    public void floatCreateUser(View view) {
        Intent createUser = new Intent(MainAdminPage.this, MainCreateUsers.class);
        startActivity(createUser);


    }

}
