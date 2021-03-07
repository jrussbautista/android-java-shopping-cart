package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText etName, etEmail, etPassword;
    private Button btnSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etName = findViewById(R.id.signUpTextInputName);
        etEmail = findViewById(R.id.signUpTextInputEmail);
        etPassword = findViewById(R.id.signUpTextInputPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        mAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Successfully sign up!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            // thrown if there already exists an account with the given email address
                            Toast.makeText(SignUpActivity.this,"Email is already taken", Toast.LENGTH_LONG).show();
                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignUpActivity.this, "Email is invalid", Toast.LENGTH_LONG).show();
                        } else if (e instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(SignUpActivity.this, "Password must be at least 6 characters long", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(SignUpActivity.this, "Unable to sign up. Please try again later", Toast.LENGTH_LONG).show();
                        }
;
                    }
                });
            }
        });

    }



}