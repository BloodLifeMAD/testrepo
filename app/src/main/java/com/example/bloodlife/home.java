package com.example.bloodlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {
    private CardView eCard,cCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        eCard=findViewById(R.id.card);
        cCard=findViewById(R.id.cContact);
        cCard=findViewById(R.id.profile);
        cCard=findViewById(R.id.event);

        eCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,BloodRequest.class));
            }
        });

        cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,Contact_Us.class));
            }
        });

        cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,ProfileActivity.class));
            }
        });

        cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this,DonationEventMain.class));
            }
        });



    }
}