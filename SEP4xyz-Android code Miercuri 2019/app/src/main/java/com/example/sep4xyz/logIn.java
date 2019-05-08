package com.example.sep4xyz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logIn extends AppCompatActivity implements View.OnClickListener {

    private EditText userName;
    private EditText userPassword;
    private Button loginBtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

         /*Owner Alexandru Vieru & Rares Bunea
       gets an instance of the fire base auth*/

        firebaseAuth = FirebaseAuth.getInstance();
        // checks to see if the user is already logged in
        if (firebaseAuth.getCurrentUser() != null) {
            Intent Login = new Intent(logIn.this, SelectBlock.class);
            startActivity(Login);
        }
        progressDialog = new ProgressDialog(this);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

    }

    @Override
    // if condition is true, run the userLogin method
    public void onClick(View view) {
        if (view == loginBtn) {
            userLogin();
        }
    }

    private void userLogin() {
        // creates string objects for the input fields
        String email = userName.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        // checks to see if the input field is empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a user name", Toast.LENGTH_SHORT).show();
            //stops the function from executing further
            return;
        }
        // checks to see if the input field is empty
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        // set's a message upon verifying credentials
        progressDialog.setMessage("Verifying credentials...");
        progressDialog.show();
        // uses fire base sign in method verification
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // if authentication is completed successfully than it will move to the next page
                if (task.isSuccessful()) {
                    finish();
                    Intent Login = new Intent(logIn.this, SelectBlock.class);
                    startActivity(Login);
                } else {
                    Toast.makeText(logIn.this, "Invalid user name or password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });
    }
}
