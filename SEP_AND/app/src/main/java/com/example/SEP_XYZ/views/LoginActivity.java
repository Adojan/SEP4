package com.example.SEP_XYZ.views;

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
import com.example.SEP_XYZ.viewmodels.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView email;
    private TextView password;
    private LoginViewModel loginViewModel;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login = findViewById(R.id.registerTechnician);
        email = findViewById(R.id.userId);
        password = findViewById(R.id.passwordId);

        progressDialog = new ProgressDialog(this);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init();

    }

    public void userLogin(View view) {


        // checks to see if the input field is empty
        if (loginViewModel.checkEmptyTextField(email)) {
            Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        // checks to see if the input field is empty
        if (loginViewModel.checkEmptyTextField(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        // set's a message upon verifying credentials
        progressDialog.setMessage("Verifying credentials...");
        progressDialog.show();


        // uses fire base sign in method verification
        loginViewModel.getmFireBaseModel().getmFIreBaseAuth().signInWithEmailAndPassword(loginViewModel.geStringFromTextView(email), loginViewModel.geStringFromTextView(password)).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // if authentication is completed successfully than it will move to the next page
                if (task.isSuccessful()) {
                    Intent Login = new Intent(LoginActivity.this, SelectRoomActivity.class);
                    startActivity(Login);
                    //check if the account belongs to the admin
                } else if (loginViewModel.checkIfAdministrator(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, AdminSelectRoomActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();


                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user name or password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
                progressDialog.dismiss();
            }
        });
    }


}
