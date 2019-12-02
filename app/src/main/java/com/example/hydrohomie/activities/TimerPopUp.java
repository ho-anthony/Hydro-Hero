package com.example.hydrohomie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hydrohomie.R;

public class TimerPopUp extends AppCompatActivity {
    EditText value;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_pop_up);
        value = findViewById(R.id.timeInput);
        cancel = findViewById(R.id.cancelTimers);
        //set size of pop up window to match phone dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.6),(int)(height*.4));
    }

    public void submitValue(View v) {
        if(value.getText().toString().trim().equals("")) {
            Toast.makeText(this,"No time set",Toast.LENGTH_SHORT).show();
            finish();
        } else if (value.getText().toString().trim().equals("0")) {
            Toast.makeText(this,"Time cannot be set to 0",Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result",value.getText().toString().trim());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }

    public void cancel(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result","Cancel");
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
