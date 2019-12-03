package com.example.hydrohomie.fragments;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.example.hydrohomie.activities.ProfileActivity;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button openProfileButton;
    private Button aboutUsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container,false );
        openProfileButton = (Button) rootView.findViewById(R.id.profile);
        openProfileButton.setOnClickListener(this);

        aboutUsButton = (Button) rootView.findViewById(R.id.aboutUs);
        aboutUsButton.setOnClickListener(this);
        return rootView;
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile:
                Intent myIntent = new Intent(SettingsFragment.this.getActivity(), ProfileActivity.class);
                startActivity(myIntent);
                break;
            case R.id.aboutUs:
                Toast.makeText(getContext(),"We, the hydro homies, are a group of students " +
                        "working to develop this app to help people stay healthy and hydrated! " +
                        "By doing this, we hope to give a helping hand to the people out there wh" +
                        "o need a reason or reminder to stay healthy.",Toast.LENGTH_LONG).show();
                break;


        }

    }
}
