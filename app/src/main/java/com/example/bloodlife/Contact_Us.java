package com.example.bloodlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Contact_Us extends AppCompatActivity {
    private EditText mName,mSubject,mMessage;
    Button mSend,mBack,mFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);


        mName=findViewById(R.id.eName);
        mSubject=findViewById(R.id.eSubject);
        mMessage=findViewById(R.id.eMessage);

        mSend=findViewById(R.id.btnSend);
        mBack=findViewById(R.id.btnBack);
        mFeed=findViewById(R.id.btnFeed);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Contact_Us.this,home.class));
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        mFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Contact_Us.this,Feedback.class));
            }
        });

    }
    private void sendMail(){
        String recipientList =mName.getText().toString();
        String[] recipients=recipientList.split(",");

        String subject =mSubject.getText().toString();
        String message=mMessage.getText().toString();

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"Select Email client"));
    }
}