package com.example.hydrohomie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.hydrohomie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progress;
    EditText email;
    EditText password;
    Button signIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        signIn = findViewById(R.id.loginButton);
        progress = findViewById(R.id.loginProgress);
        mAuth = FirebaseAuth.getInstance();
    }

    //when login button is pressed
    public void onLogIn(View v) {
        if(email.getText().toString().trim().equals("") || password.getText().toString().trim()
                .equals("")) {
            Toast.makeText(getApplicationContext(),"Email/Password is required",
                    Toast.LENGTH_SHORT).show();
        } else {
            signIn.setEnabled(false);
            progress.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText()
                    .toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progress.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                progress.setVisibility(View.GONE);
                                signIn.setEnabled(true);
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //navigates to register activity
    public void onSignUp(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
