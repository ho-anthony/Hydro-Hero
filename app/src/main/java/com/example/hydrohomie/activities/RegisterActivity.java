package com.example.hydrohomie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hydrohomie.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password, confirmPass;
    Button submit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.nameInput);
        email = findViewById(R.id.emailInput);
        password = findViewById(R.id.passwordInput);
        confirmPass = findViewById(R.id.confirmInput);
        submit = findViewById(R.id.registerButton);
        mAuth = FirebaseAuth.getInstance();
    }

    public void submitInfo(View v) {
        if(name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
        } else if(email.getText().toString().trim().equals("") || !isValidEmail(email.getText())) {
            Toast.makeText(this, "Email is required/invalid", Toast.LENGTH_SHORT).show();
        } else if(password.getText().toString().trim().length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
        } else if(!password.getText().toString().equals(confirmPass.getText().toString())) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"failed :(",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public boolean isValidEmail(CharSequence input) {
        return (!TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches());
    }
}
