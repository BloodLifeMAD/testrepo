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







public class ProfileActivity extends AppCompatActivity {


    EditText u_name,u_blood_gr,u_no_unit,u_hospital,u_po_nu,u_hometown;
    Button Savebtt,showbtt,updatebtt,deletebtt;
    DatabaseReference dbRef;
    Profile UP;

    private void clearControls() {

        u_name.setText("");
        u_blood_gr.setText("");
        u_no_unit.setText("");
        u_hospital.setText("");
        u_po_nu.setText("");
        u_hometown.setText("");

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        u_name = findViewById(R.id.Uname);
        u_blood_gr = findViewById(R.id.ublood);
        u_no_unit=findViewById(R.id.Uunit);
        u_hospital=findViewById(R.id.Uhospital);
        u_po_nu=findViewById(R.id.uphone);
        u_hometown=findViewById(R.id.Uhome);

        Savebtt = findViewById(R.id.Savebt);
        showbtt=findViewById(R.id.showbt);
        updatebtt=findViewById(R.id.updatebt);
        deletebtt=findViewById(R.id.deletebt);



        UP=new Profile();


        //Delete data
        deletebtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Profile").child("P1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });


        Savebtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Profile");

                try {
                    if (TextUtils.isEmpty(u_name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Name", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(u_blood_gr.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the blood group", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(u_no_unit.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Units", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(u_hospital.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hospital", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(u_po_nu.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hospital", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(u_hometown.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hospital", Toast.LENGTH_SHORT);

                    else {

                        UP.setU_name(u_name.getText().toString().trim());
                        UP.setU_blood_gr(u_blood_gr.getText().toString().trim());
                        UP.setU_no_unit(Integer.parseInt(u_no_unit.getText().toString().trim()));
                        UP.setU_hospital(u_hospital.getText().toString().trim());
                        UP.setU_po_nu(Integer.parseInt(u_po_nu.getText().toString().trim()));
                        UP.setU_hometown(u_hometown.getText().toString().trim());



                        //dbRef.push().setValue(RE);
                        dbRef.child("P1").setValue(UP);
                        Toast.makeText(getApplicationContext(), "Data entered success", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid ContactNumber", Toast.LENGTH_SHORT).show();
                }
            }

        });
        //show the data what is in the database
        showbtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Profile").child("P1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChildren()){
                            u_name.setText(dataSnapshot.child("u_name").getValue().toString());
                            u_blood_gr.setText(dataSnapshot.child("u_blood_gr").getValue().toString());
                            u_no_unit.setText(dataSnapshot.child("u_no_unit").getValue().toString());
                            u_hospital.setText(dataSnapshot.child("u_hospital").getValue().toString());
                            u_po_nu.setText(dataSnapshot.child("u_po_nu").getValue().toString());
                            u_hometown.setText(dataSnapshot.child("u_hometown").getValue().toString());




                        }
                        else
                            Toast.makeText(getApplicationContext(),"NO DATA SOURCE TO DISPLAY",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        updatebtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upRef=FirebaseDatabase.getInstance().getReference().child("Profile");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("P1"))
                            try {


                                UP.setU_name(u_name.getText().toString().trim());
                                UP.setU_blood_gr(u_blood_gr.getText().toString().trim());
                                UP.setU_no_unit(Integer.parseInt(u_no_unit.getText().toString().trim()));
                                UP.setU_hospital(u_hospital.getText().toString().trim());
                                UP.setU_po_nu(Integer.parseInt(u_po_nu.getText().toString().trim()));
                                UP.setU_hometown(u_hometown.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Profile");
                                dbRef.setValue(UP);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data Updated successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
                            }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });






    }
}


