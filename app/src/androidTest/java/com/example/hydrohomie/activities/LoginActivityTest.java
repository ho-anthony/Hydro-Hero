package com.example.hydrohomie.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import com.example.hydrohomie.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule  = new ActivityTestRule<>(LoginActivity.class);
    private LoginActivity lActivity = null;

    @Before
    public void setUp() throws Exception {
        lActivity = loginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLoginLogo() {
        ImageView loginLogo = lActivity.findViewById((R.id.LoginLogo));
        assertNotNull(loginLogo);
    }

    @Test
    public void testNameEdit() {
        EditText loginEmail = lActivity.findViewById((R.id.loginEmail));
        assertNotNull(loginEmail);
    }

    @Test
    public void testEmailEdit() {
        EditText loginPassword = lActivity.findViewById((R.id.loginPassword));
        assertNotNull(loginPassword);
    }

    @Test
    public void testLoginButton() {
        Button loginButton = lActivity.findViewById(R.id.loginButton);
        assertNotNull(loginButton);
    }

    @Test
    public void testSignUpPrompt() {
        TextView signUpPrompt = lActivity.findViewById(R.id.signupPrompt);
        assertNotNull(signUpPrompt);
    }

    @Test
    public void testForgotPassword() {
        TextView forgotPassword = lActivity.findViewById(R.id.forgotPassword);
        assertNotNull(forgotPassword);
    }

    @After
    public void tearDown() throws Exception {
        lActivity = null;
    }
}