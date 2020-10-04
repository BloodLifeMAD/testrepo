package com.example.bloodlife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    EditText namedata,emaildata,messagedata;
    Button send,view;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        namedata=findViewById(R.id.nameData);
        emaildata=findViewById(R.id.emailData);
        messagedata=findViewById(R.id.messageData);

        send=findViewById(R.id.btnSent);
        view=findViewById(R.id.btnget);

        database= FirebaseDatabase.getInstance().getReference();

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.setEnabled(true);
                final String name = namedata.getText().toString();
                final String email= emaildata.getText().toString();
                final String message=messagedata.getText().toString();

                DatabaseReference child_name=database.child("Name");
                child_name.setValue(name);
                if(name.isEmpty()){
                    namedata.setError("This is required field");
                    send.setEnabled(false);
                }
                else{
                    namedata.setError(null);
                    send.setEnabled(true);
                }
                DatabaseReference child_email=database.child("Email");
                child_email.setValue(email);
                if(email.isEmpty()){
                    emaildata.setError("This is required field");
                    send.setEnabled(false);
                }
                else{
                    emaildata.setError(null);
                    send.setEnabled(true);
                }
                DatabaseReference child_message=database.child("Message");
                child_email.setValue(message);
                if(message.isEmpty()){
                    messagedata.setError("This is required field");
                    send.setEnabled(false);
                }
                else{
                    messagedata.setError(null);
                    send.setEnabled(true);
                }
                Toast.makeText(Feedback.this,"Your Data Entered Successfully",Toast.LENGTH_SHORT).show();
                namedata.setText("");
                emaildata.setText("");
                messagedata.setText("");
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(Feedback.this)
                                .setTitle("Sended Details:")
                                .setMessage("Name :" +name +"\n\nEmail :"+email +"\n\n Message :"+message)
                                .show();
                    }
                });


            }
        });


    }
}