package com.example.hydrohomie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hydrohomie.R;

public class PopUp extends Activity {

    private RadioGroup drinkOptionGroup;
    private RadioButton radioDrink;
    private Button selectButton;
    //user input (fix)
    private int finalValue;

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

        addListenerOnButton();
    }

    public void addListenerOnButton(){
        drinkOptionGroup = findViewById(R.id.drinkOptions);
        selectButton = findViewById(R.id.addDrinkButton);

        selectButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets selected radioButton from group
                int selectedID = drinkOptionGroup.getCheckedRadioButtonId();
                radioDrink = findViewById(selectedID);

                //print amount added
                Toast.makeText(PopUp.this,
                        radioDrink.getText() + " added!", Toast.LENGTH_SHORT).show();

                finish();
                //IMPORTANT: if no button selected and "add drink clicked", app crashes (fix)
            }
        });



    }

}
