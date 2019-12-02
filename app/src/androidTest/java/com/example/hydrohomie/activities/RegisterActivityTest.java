package com.example.hydrohomie.activities;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.rule.ActivityTestRule;

import com.example.hydrohomie.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {
    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);
    private RegisterActivity rActivity = null;

    @Before
    public void setUp() throws Exception {
        rActivity = registerActivityActivityTestRule.getActivity();
    }

    @Test
    public void testImage() {
        ImageView imageView = rActivity.findViewById((R.id.imageView));
        assertNotNull(imageView);
    }

    @Test
    public void testNameEdit() {
        EditText nameInput = rActivity.findViewById((R.id.nameInput));
        assertNotNull(nameInput);
    }

    @Test
    public void testEmailEdit() {
        EditText emailInput = rActivity.findViewById((R.id.emailInput));
        assertNotNull(emailInput);
    }

    @Test
    public void testPasswordEdit() {
        EditText passwordInput = rActivity.findViewById((R.id.passwordInput));
        assertNotNull(passwordInput);
    }

    @Test
    public void testConfirmEdit() {
        EditText confirmInput = rActivity.findViewById((R.id.confirmInput));
        assertNotNull(confirmInput);
    }

    @After
    public void tearDown() throws Exception {
        rActivity = null;
    }
}