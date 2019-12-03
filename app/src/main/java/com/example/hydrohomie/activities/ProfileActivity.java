package com.example.hydrohomie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hydrohomie.R;

public class ProfileActivity extends AppCompatActivity {
    private Button saveButton;
    private EditText editName;
    private EditText editAge;
    private EditText editWeight;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String WEIGHT = "weight";

    public String savedName;
    public Integer savedAge;
    public Integer savedWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadData();
        editName = (EditText) findViewById(R.id.nameEntry);
        editAge = (EditText) findViewById(R.id.ageEntry);
        editWeight = (EditText) findViewById(R.id.weightEntry);
        saveButton = (Button) findViewById(R.id.save);



        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveData();
            }
        });


    }

    public void saveData(){
        if(editName.getText().toString().trim().equals("")||editAge.getText().toString().trim().equals("")
                ||editWeight.getText().toString().trim().equals("")||Integer.parseInt(editWeight.getText().toString().trim())<=0||Integer.parseInt(editAge.getText().toString().trim())<=0){
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NAME, editName.getText().toString());
            editor.putInt(AGE, Integer.parseInt(editAge.getText().toString()));
            editor.putInt(WEIGHT, Integer.parseInt(editWeight.getText().toString()));
            editor.apply();
            finish();
        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        savedName = sharedPreferences.getString(NAME,"");
        savedAge = sharedPreferences.getInt(AGE,18);
        savedWeight = sharedPreferences.getInt(WEIGHT,90);
    }


}
