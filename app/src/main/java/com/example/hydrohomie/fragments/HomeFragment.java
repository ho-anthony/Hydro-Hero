package com.example.hydrohomie.fragments;

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


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,false );
        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addButton:
                test(v);
                break;
        }

    }

    public void test(View v) {
        Toast.makeText(getActivity(), "working!", Toast.LENGTH_SHORT).show();
    }
}
