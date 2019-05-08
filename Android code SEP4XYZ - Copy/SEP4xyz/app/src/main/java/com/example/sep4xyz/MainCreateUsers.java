package com.example.sep4xyz;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainCreateUsers extends AppCompatActivity implements View.OnClickListener {
    /*-----Owner Alexandru Vieru & Rares Bunea
    instantiating the views and the button*/
    private EditText userId;
    private EditText passwordId;
    private Button register;
    private TextView signIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_users);

        // gets an instance of the fire base object
        firebaseAuth = FirebaseAuth.getInstance();
        /* creates an object for the progress dialog and initializes it */
        progressDialog = new ProgressDialog(this);
        userId = findViewById(R.id.userId);
        passwordId = findViewById(R.id.passwordId);
        register = findViewById(R.id.register);
        signIn = findViewById(R.id.signIn);
        register.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    // method that initializes the input fields as strings and uses a boolean approach to check if the fields are empty or not
    private void registerUser() {
        String email = userId.getText().toString().trim();
        String password = passwordId.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }

        progressDialog.setMessage("Verifying credentials...");
        progressDialog.show();


        // this creates a user account with two parameters of email and password on the fire base
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @SuppressLint("ByteOrderMark")
            @Override
            // this method checks to see if the registration has been successful or not
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainCreateUsers.this, "Account has been registered", Toast.LENGTH_SHORT).show();
                    Intent cretaeUsers = new Intent(MainCreateUsers.this, MainCreateUsers.class);
                    startActivity(cretaeUsers);

                } else {
                    Toast.makeText(MainCreateUsers.this, "Unable to create user account", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }


    // creates the on click listener functionality for the register button and the signIn text
    @Override
    public void onClick(View view) {
        if (view == register) {
            // uses a boolean to check if the register button is pressed it wil execute the  registerUser() method written above
            registerUser();
        } else if (view == signIn) {
            Intent mainIntent = new Intent(MainCreateUsers.this, MainActivity.class);
            startActivity(mainIntent);
        }
    }


}


