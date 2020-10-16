package com.example.bloodlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Blood extends AppCompatActivity {


    EditText Name, email, Address, Div, Phone;
    Spinner gender,BloodGroup;
    Button btadd, BtnShow,btnUpdate, btnDel;
    DatabaseReference dbRef;

    Donar DE;

    private void clearControls() {

        Name.setText("");
        email.setText("");
        Address.setText("");
        Div.setText("");
        Phone.setText("");
        gender.setSelection ( Integer.parseInt ( "" ) );
        BloodGroup.setSelection ( Integer.parseInt ( "" ) );


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donate);


        Name = findViewById(R.id.name);
        email = findViewById(R.id.bgrp);
        Address = findViewById(R.id.address);
        Div = findViewById(R.id.hos);
        Phone = findViewById(R.id.Pno);
        gender = findViewById(R.id.gender);
        BloodGroup=findViewById ( R.id.BloodGroup );


        btadd = findViewById(R.id.add);
        BtnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUp);
        btnDel = findViewById ( R.id.btDel);

        DE = new Donar();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Donar");

                if(Phone.getText().length()==10)


                try {
                    if (TextUtils.isEmpty(Name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Name", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the email address", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Address.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Address", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Div.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the district ", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(gender.getSelectedItem ().toString()))
                        Toast.makeText(getApplicationContext(), "Please select the gender  ", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(BloodGroup.getSelectedItem ().toString()))
                        Toast.makeText(getApplicationContext(), "Please select the Bloo dgroup  ", Toast.LENGTH_SHORT);

                    else {

                        DE.setEmail (email.getText().toString().trim());
                        DE.setPhone (Integer.parseInt(Phone.getText().toString().trim()));
                        DE.setName(Name.getText().toString().trim());
                        DE.setDiv (Div.getText().toString().trim());
                        DE.setAddress (Address.getText().toString().trim());
                        DE.setGender (gender.getSelectedItem ().toString ().trim());
                        DE.setBloodGroup (BloodGroup.getSelectedItem ().toString ().trim());

                        dbRef.child("DE").setValue(DE);
                        Toast.makeText(getApplicationContext(), "Data entered success", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid ContactNumber", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //show the data what is in the database
        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Donar").child("DE");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            Name.setText(dataSnapshot.child("name").getValue().toString());
                            email.setText(dataSnapshot.child("email").getValue().toString());
                            Address.setText(dataSnapshot.child("address").getValue().toString());
                            Div.setText(dataSnapshot.child("div").getValue().toString());
                            Phone.setText(dataSnapshot.child("phone").getValue().toString());


                        } else
                            Toast.makeText(getApplicationContext(), "NO DATA SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
//update data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Donar");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                        if (dataSnapshot.hasChild ( "DE" ))

                            if(Phone.getText().length()==10)
                            try {
                                DE.setName ( Name.getText ().toString ().trim () );
                                DE.setEmail ( email.getText ().toString ().trim () );
                                DE.setAddress ( Address.getText ().toString ().trim () );
                                DE.setDiv ( Div.getText ().toString ().trim () );
                                DE.setPhone ( Integer.parseInt ( Phone.getText ().toString ().trim () ) );
                                DE.setGender ( gender.getSelectedItem ().toString ().trim () );
                                DE.setBloodGroup ( BloodGroup.getSelectedItem ().toString ().trim () );

                                dbRef = FirebaseDatabase.getInstance ().getReference ().child ( "Donar" );
                                dbRef.setValue ( DE );
                                clearControls ();
                                Toast.makeText ( getApplicationContext (), "Data Updated successfully", Toast.LENGTH_SHORT ).show ();
                            }
                        catch(NumberFormatException e){
                                    Toast.makeText ( getApplicationContext (), "Data Updated successfully", Toast.LENGTH_SHORT ).show ();
                                }


                        else {
                            Toast.makeText ( getApplicationContext (), "No source to update", Toast.LENGTH_SHORT ).show ();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        //Delete data
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Donar");
                dbref.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });


    }
}