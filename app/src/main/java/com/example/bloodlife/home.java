package com.example.bloodlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {
    private CardView eCard,cCard1,cCard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        eCard=findViewById(R.id.card);
        cCard1=findViewById(R.id.cContact);
        cCard2=findViewById(R.id.event);

        eCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,BMI.class));
            }
        });

        cCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,Contact_Us.class));
            }
        });

        cCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,MainEvent.class));
            }
        });



    }
}