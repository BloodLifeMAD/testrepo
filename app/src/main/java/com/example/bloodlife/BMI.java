package com.example.bloodlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

    private EditText height,weight;
    private TextView result;
    Button donor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        height = (EditText) findViewById(R.id.Height);
        weight = (EditText) findViewById(R.id.Weight);
        result = (TextView) findViewById(R.id.Result);
        donor=findViewById(R.id.btnDon);


        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BMI.this,Blood.class));
            }
        });


    }
        public void CalculateBMI(View V)
        {
            String h = height.getText().toString();
            String w = weight.getText().toString();
            if(h!=null && !"".equals(h) && w!=null && !"".equals(w))
            {
                float hf=Float.parseFloat(h);
                float wf=Float.parseFloat(w);
                float bmi=(wf/(hf*hf))*10000;
                String lbl="";

                if(bmi<=18.5){
                    lbl=getString(R.string.under);

                }
                else if(bmi>24.99 && bmi<=29.99)
                {
                    lbl=getString(R.string.over);
                }
                else if(bmi>=18.5 && bmi<=24.99)
                {
                    lbl=getString(R.string.normal);
                }
                else
                {
                    lbl=getString(R.string.obesity);
                }
                result.setText(bmi+"\n\n "+lbl);
            }


        }
}