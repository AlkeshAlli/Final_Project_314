package com.example.finalprojectgroup8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Availability extends AppCompatActivity {
    CheckBox sun,mon,tue,wed,thurs,fri,sat;
    Boolean bsun,bmon,btue,bwed,bthurs,bfri,bsat;
    Button setav;
    String usernamesession;
    FirebaseDatabase rootnode;
    DatabaseReference reference,newreference,subref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        SharedPreferences preferences = getSharedPreferences("com.example.finalprojectgroup8", Context.MODE_PRIVATE);
        sun = findViewById(R.id.sunday);
        mon = findViewById(R.id.monday);
        tue = findViewById(R.id.tuesday);
        wed = findViewById(R.id.wednesday);
        thurs =findViewById(R.id.thursday);
        fri = findViewById(R.id.friday);
        sat = findViewById(R.id.saturday);
        setav = findViewById(R.id.setAv);
        final Boolean userbool = preferences.getBoolean("asNanny",true);
        usernamesession = preferences.getString("username", null);
        if(userbool == true)
        {
            reference = FirebaseDatabase.getInstance().getReference("Profile Creation AsNanny");
        }
        else
        {
            reference = FirebaseDatabase.getInstance().getReference("Profile Creation For Nanny");
        }
        newreference = reference.child(usernamesession);
        subref = newreference.child("availability");

        setav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sun.isChecked())
                    bsun=true;
                else
                    bsun=false;
                subref.child("sunday").setValue(bsun);
                if(mon.isChecked())
                    bmon=true;
                else
                    bmon=false;
                subref.child("monday").setValue(bmon);
                if(tue.isChecked())
                    btue=true;
                else
                    btue=false;
                subref.child("tuesday").setValue(btue);
                if(wed.isChecked())
                    bwed=true;
                else
                    bwed=false;
                subref.child("wednesday").setValue(bwed);
                if(thurs.isChecked())
                    bthurs=true;
                else
                    bthurs=false;
                subref.child("thursday").setValue(bthurs);
                if(fri.isChecked())
                    bfri=true;
                else
                    bfri=false;
                subref.child("friday").setValue(bfri);
                if(sat.isChecked())
                    bsat=true;
                else
                    bsat=false;
                subref.child("saturday").setValue(bsat);

                finish();
            }

        });
    }
}
