package com.example.bloodlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonationEventMain extends AppCompatActivity {

    EditText ename, address, date, time, contact;
    Button addbtn, viewbtn, updatebtn, deletebtn;
    DatabaseReference dbref;
    DonationEvent de;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_event_main);

        ename = findViewById(R.id.eventname);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        contact = findViewById(R.id.phone);

        addbtn = findViewById(R.id.addbtn);
        viewbtn = findViewById(R.id.viewbtn);
        updatebtn = findViewById(R.id.updbtn);
        deletebtn = findViewById(R.id.delbtn);

        de = new DonationEvent();

        //Delete data
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("DonationEvent").child("E1");
                dbref.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });


        //Insert data into database
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("DonationEvent");
                try {
                    if (TextUtils.isEmpty(ename.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Event Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Event Destination Address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(date.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Event Date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(time.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Event Time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(contact.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Event Contact Number", Toast.LENGTH_SHORT).show();
                    else {

                        de.setEname(ename.getText().toString().trim());
                        de.setAddress(address.getText().toString().trim());
                        de.setDate(date.getText().toString().trim());
                        de.setTime(time.getText().toString().trim());
                        de.setMobile(Integer.parseInt(contact.getText().toString().trim()));

                        //dbRef.push().setValue(de);
                        dbref.child("E1").setValue(de);
                        Toast.makeText(getApplicationContext(), "Details Successfully Added!", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //to show the data that inside the database
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("DonationEvent/E1");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            ename.setText(dataSnapshot.child("ename").getValue().toString());
                            address.setText(dataSnapshot.child("address").getValue().toString());
                            date.setText(dataSnapshot.child("date").getValue().toString());
                            time.setText(dataSnapshot.child("time").getValue().toString());
                            contact.setText(dataSnapshot.child("mobile").getValue().toString());

                        } else
                            Toast.makeText(getApplicationContext(), "No data source to display", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference();
                dbref.child("DonationEvent").child("E1").child("ename").setValue(ename.getText().toString().trim());
                dbref.child("DonationEvent/E1/address").setValue(address.getText().toString().trim());
                Toast.makeText(DonationEventMain.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                clearControls();
            }

        });
    }
    private void clearControls() {
        ename.setText("");
        address.setText("");
        date.setText("");
        time.setText("");
        contact.setText("");
    }

}

