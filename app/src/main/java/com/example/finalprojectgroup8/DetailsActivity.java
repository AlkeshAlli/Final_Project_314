package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {
    ImageView profilepicture;
    TextView profilename,profilelocation,profiledescription,profilewage,profileexperience,profileage;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6,checkbox7;

    //TextView profileemail;
    Button rate;

    String name;
    //String storeusername, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int pic = R.drawable.logo;
        profilepicture = findViewById(R.id.asprofilepic);
        profilename = findViewById(R.id.asprofilename);
        profilelocation = findViewById(R.id.asprofilelocation);
        profileage = findViewById(R.id.asprofileage);
        profiledescription = findViewById(R.id.asprofiledescription);
        //profileemail = findViewById(R.id.asprofileemail);
        profileexperience = findViewById(R.id.asprofileexperience);
        profilewage = findViewById(R.id.asprofilewage);



        rate = findViewById(R.id.asrate);
        //storeusername = getIntent().getStringExtra("takeusername");
        check();
        setData();

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DetailsActivity.this,Rating.class);
                i.putExtra("username",name);
                startActivity(i);
            }
        });
    }
    private void check(){
            if(getIntent().hasExtra("viewname")){
                name=getIntent().getStringExtra("viewname").toString();
                //Log.d("name test",name);

            }
            else {
                Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();

            }
    }
    private void setData(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Profile Creation AsNanny");
        Query checkuser = reference.orderByChild("username").equalTo(name);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Log.d("name test",name);
                    String dbage= (String) dataSnapshot.child(name).child("age").getValue(String.class);
                    String dbwage= (String) dataSnapshot.child(name).child("rate").getValue(String.class);
                    String dblocation=dataSnapshot.child(name).child("location").getValue(String.class);
                    String dbexperience= (String) dataSnapshot.child(name).child("experience").getValue(String.class);
                    String dbdescription=dataSnapshot.child(name).child("description").getValue(String.class);
                    int pic = R.drawable.logo;
                    profilepicture.setImageResource(pic);
                    profilename.setText(name);
                    profilelocation.setText("Location: "+ dblocation);
                    profileage.setText("Age: "+dbage.toString()+" Years");
                    profiledescription.setText("Description: \n"+dbdescription);
                    profileexperience.setText("Experience "+dbexperience.toString()+" Years");
                    profilewage.setText("Expected Wage: $"+dbwage.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}