package com.example.hydrohomie.fragments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hydrohomie.R;
import com.example.hydrohomie.database.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HomeFragment extends Fragment implements View.OnClickListener {

    Button addButton;
    DatabaseHelper database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container,false );
        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        database = new DatabaseHelper(getContext());
        return rootView;
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addButton:
                addDrink(v);
                break;
        }

    }

    public void addDrink(View v) {
        DateFormat day = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        boolean inserted = database.insertNewDrink(day.format(date), 8);
        if(inserted) {
            Toast.makeText(getContext(),"Inserted!",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"FAILED!",Toast.LENGTH_SHORT).show();
        }
    }
}
