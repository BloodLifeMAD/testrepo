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

public class Blood extends AppCompatActivity {


    EditText Name, blgrp, Unit, Hos, Phone;
    Button btadd, BtnShow, btnUpdate, btnDel;
    DatabaseReference dbRef;

    Donar DE;

    private void clearControls() {

        Name.setText("");
        blgrp.setText("");
        Unit.setText("");
        Hos.setText("");
        Phone.setText("");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donate);


        Name = findViewById(R.id.name);
        blgrp = findViewById(R.id.bgrp);
        Unit = findViewById(R.id.units);
        Hos = findViewById(R.id.hos);
        Phone = findViewById(R.id.Pno);

        btadd = findViewById(R.id.add);
        BtnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUp);
        btnDel = findViewById ( R.id.btDel);

        DE = new Donar();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Donar");

                try {
                    if (TextUtils.isEmpty(Name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Name", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(blgrp.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the blood group", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Unit.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Units", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(Hos.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hospital  ", Toast.LENGTH_SHORT);

                    else {

                        DE.setBlgrp(blgrp.getText().toString().trim());
                        DE.setPno(Integer.parseInt(Phone.getText().toString().trim()));
                        DE.setName(Name.getText().toString().trim());
                        DE.setHos(Hos.getText().toString().trim());
                        DE.setUnits(Unit.getText().toString().trim());


                        //dbRef.push().setValue(RE);
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
                            blgrp.setText(dataSnapshot.child("blgrp").getValue().toString());
                            Unit.setText(dataSnapshot.child("units").getValue().toString());
                            Hos.setText(dataSnapshot.child("hos").getValue().toString());
                            Phone.setText(dataSnapshot.child("pno").getValue().toString());


                        } else
                            Toast.makeText(getApplicationContext(), "NO DATA SOURCE TO DISPLAY", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Donar");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("DE"))
                            try {
                                DE.setName(Name.getText().toString().trim());
                                DE.setBlgrp(blgrp.getText().toString().trim());
                                DE.setUnits(Unit.getText().toString().trim());
                                DE.setHos(Hos.getText().toString().trim());
                                DE.setPno(Integer.parseInt(Phone.getText().toString().trim()));

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Donar");
                                dbRef.setValue(DE);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data Updated successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        else
                            Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
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