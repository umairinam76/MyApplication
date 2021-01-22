package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth myauth;
    private EditText email,password;
    private Button Signin;
    private TextView Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    private void init(){
        myauth=FirebaseAuth.getInstance();
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        Signin=findViewById(R.id.signinlogin);
        Signin.setOnClickListener(this);
        Signup=findViewById(R.id.signuplogin);
        Signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signinlogin:
                if (email.getText().toString().isEmpty()) {
                    email.setError("INVALID EMAIL");
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Invalid password");
                    if (password.length() < 8) {
                        password.setError("INVALID LENGHT");
                    }
                } else {
                    String emaillogin = email.getText().toString();
                    String passwordlogin = password.getText().toString();
                    myauth.signInWithEmailAndPassword(emaillogin, passwordlogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login.this, "LOGIN SUCCESFULL", Toast.LENGTH_SHORT).show();
//                                Intent MAINPAGE = new In
//                                tent(Login.this, Mainpage.class);
//                                startActivity(MAINPAGE);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.signuplogin:
                    Intent MAINActivity=new Intent(Login.this,MainActivity.class);
                    startActivity(MAINActivity);
                    break;
                }
        }
    }