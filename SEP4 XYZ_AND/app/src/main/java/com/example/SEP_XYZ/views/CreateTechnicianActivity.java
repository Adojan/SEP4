package com.example.SEP_XYZ.views;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.viewmodels.CreateTechnicianViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class CreateTechnicianActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    private TextView reenterPassword;

    private Button create;

    private CreateTechnicianViewModel createTechnicianViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_users);

        email = findViewById(R.id.userId);
        password = findViewById(R.id.passwordId);
        reenterPassword = findViewById(R.id.passwordReenter);


        create = findViewById(R.id.registerTechnician);

        createTechnicianViewModel = ViewModelProviders.of(this).get(CreateTechnicianViewModel.class);
        createTechnicianViewModel.init();

        progressDialog = new ProgressDialog(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });
    }


    public void registerUser(View view) {


        if (createTechnicianViewModel.checkEmptyTextField(email)) {
            Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        if (createTechnicianViewModel.checkEmptyTextField(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        if (createTechnicianViewModel.checkEmptyTextField(reenterPassword)) {
            Toast.makeText(this, "Please re-enter your password", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        if (createTechnicianViewModel.checkLenght(password.toString())) {
            Toast.makeText(this, "The password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }

        if (createTechnicianViewModel.geStringFromTextView(password).equals(createTechnicianViewModel.geStringFromTextView(reenterPassword))) {

            progressDialog.setMessage("Verifying credentials...");
            progressDialog.show();


            // this creates a user account with two parameters of email and password on the fire base
            createTechnicianViewModel.getmFireBaseModel().getmFIreBaseAuth().createUserWithEmailAndPassword(createTechnicianViewModel.geStringFromTextView(email), createTechnicianViewModel.geStringFromTextView(password)).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @SuppressLint("ByteOrderMark")
                @Override
                // this method checks to see if the registration has been successful or not
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateTechnicianActivity.this, "Account has been registered", Toast.LENGTH_SHORT).show();
                        Intent cretaeUsers = new Intent(CreateTechnicianActivity.this, AdminSelectRoomActivity.class);
                        startActivity(cretaeUsers);

                    } else {
                        Toast.makeText(CreateTechnicianActivity.this, "Unable to create user account", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
    }
}

