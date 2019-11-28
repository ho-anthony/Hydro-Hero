package com.example.hydrohomie.fragments;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.example.hydrohomie.activities.ProfileActivity;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button openProfileButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container,false );
        openProfileButton = (Button) rootView.findViewById(R.id.profile);
        openProfileButton.setOnClickListener(this);
        return rootView;
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile:
                Intent myIntent = new Intent(SettingsFragment.this.getActivity(), ProfileActivity.class);
                startActivity(myIntent);
                break;


        }

    }
}
