package com.example.hydrohomie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.hydrohomie.R;

public class PopUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drink_select_popup);

        //set size of pop up window to match phone dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height =dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }

}
