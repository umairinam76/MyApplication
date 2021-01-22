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

import org.w3c.dom.Text;

import static android.app.ProgressDialog.show;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth myauth;
    private EditText email,password;
    private Button Signup;
    private TextView signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

   private void init() {
        myauth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailreg);
        password = findViewById(R.id.passwordreg);
        Signup = findViewById(R.id.signupreg);
        Signup.setOnClickListener(this);
        signin = findViewById(R.id.signinreg);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signupreg:
              if (email.getText().toString().isEmpty()){
                  email.setError("Invalid email");
              }
              if(password.getText().toString().isEmpty()){
              if(password.length()<8){
                  password.setError("Invalid password");
              }}
              else {
                  String email$ = email.getText().toString();
                  String password$ = password.getText().toString();
                  myauth.createUserWithEmailAndPassword(email$, password$).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task){
                          if(task.isSuccessful()){
                       Intent login = new Intent(MainActivity.this,Login.class);
                         startActivity(login);
                              Toast.makeText(MainActivity.this, "SIGN IN SUCCESFULLY", Toast.LENGTH_SHORT).show();
                         finish();
                          }else
                              Toast.makeText(MainActivity.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                      }
                  });
              }
              break;
            case R.id.signinreg:
                if (signin.isPressed()){
                Intent login =new Intent(MainActivity.this,Login.class);
                startActivity(login);
                finish();
                break;
        }


        }
    }}
