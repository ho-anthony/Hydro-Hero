package com.example.hydrohomie.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.rule.ActivityTestRule;

import com.example.hydrohomie.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileActivityTest {
    @Rule
    public ActivityTestRule<ProfileActivity> profileActivityActivityTestRule = new ActivityTestRule<>(ProfileActivity.class);
    private ProfileActivity pActivity = null;

    @Before
    public void setUp() throws Exception {
        pActivity = profileActivityActivityTestRule.getActivity();
    }

    @Test
    public void testImage() {
        ImageView progileImage = pActivity.findViewById((R.id.ProfileImage));
        assertNotNull(progileImage);
    }

    @Test
    public void testLaunch() {
        Button save = pActivity.findViewById(R.id.save);
        assertNotNull(save);

    }

    @Test
    public void testName() {
        View name = pActivity.findViewById((R.id.fullName));
        assertNotNull(name);
    }

    @Test
    public void testWeight() {
        View weight = pActivity.findViewById((R.id.Weight));
        assertNotNull(weight);
    }

    @Test
    public void testAge() {
        View age = pActivity.findViewById((R.id.Age));
        assertNotNull(age);
    }

    @Test
    public void testAgeEdit() {
        EditText ageEntry = pActivity.findViewById((R.id.ageEntry));
        assertNotNull(ageEntry);
    }

    @Test
    public void testNameEdit() {
        EditText nameEntry = pActivity.findViewById((R.id.nameEntry));
        assertNotNull(nameEntry);
    }

    @Test
    public void testWeightEdit() {
        EditText weightEntry = pActivity.findViewById((R.id.weightEntry));
        assertNotNull(weightEntry);
    }


    @After
    public void tearDown() throws Exception {
        pActivity = null;
    }
}