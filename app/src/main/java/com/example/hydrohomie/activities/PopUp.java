package com.example.hydrohomie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hydrohomie.R;

public class PopUp extends Activity {

    private RadioGroup drinkOptionGroup;
    private RadioButton radioDrink;
    private Button selectButton;
    //user input (fix)
    public int finalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drink_select_popup);

        //set size of pop up window to change based on phone dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels; //get width of phone screen
        int height = dm.heightPixels; //get height of phone screen
        getWindow().setLayout((int)(width*.8),(int)(height*.5)); //set pop up size

        confirmFinalAmount();
    }

    //method that changes finalAmount value based on user radio button selection for default options
    public int findFinalAmount(View view){
        boolean checked = ((RadioButton) view).isChecked();
        //find which option was selected
        switch(view.getId()){
            case R.id.option1:
                if(checked){
                    finalAmount = 8;
                }
                break;
            case R.id.option2:
                if(checked){
                    finalAmount = 12;
                }
                break;
            case R.id.option3:
                if(checked){
                    finalAmount = 16;
                }
                break;
        }
        return finalAmount;
    }

    public void confirmFinalAmount(){
        selectButton = findViewById(R.id.addDrinkButton);

        selectButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks for custom amount if used
                RadioButton custom;
                custom = findViewById(R.id.optionCustom);
                if(custom.isChecked()){
                    //creates custom amount from editText
                    int customVal = 0;
                    EditText amt = findViewById(R.id.customAmount);
                    String temp = amt.getText().toString();
                    if (!"".equals(temp)){
                        customVal = Integer.parseInt(temp);
                    }
                    finalAmount = customVal;
                }

                //print amount added
                if(finalAmount == 0){
                    Toast.makeText(PopUp.this,
                            "Stay hydrated!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PopUp.this, "You drank " + finalAmount +
                            " oz. added, nice!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

}
