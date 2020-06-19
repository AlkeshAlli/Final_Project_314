package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ProfileCreationForNanny extends AppCompatActivity {
    EditText editdescrip,editlocation,editfullname,editperhour,editchildren;
    String savedescrip,saveloc,savefname,savehour,savechild;
    TextView textView;
    Button save,updatebutton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SharedPreferences preferences;
    String userfromsession;
    JavaDetailsCreationClass Creationdetails;
    Boolean flagcheck=false;
    String showdescp,showloc,showfname,showrate,showchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation_for_nanny);
        savedetailsmethod();
        editperhour = findViewById(R.id.edithour);
        editfullname = findViewById(R.id.editfname);
        editlocation = findViewById(R.id.editloc);
        editdescrip = findViewById(R.id.editdescription);
        editchildren= findViewById(R.id.editkids);
        updatebutton= findViewById(R.id.update);
        save = findViewById(R.id.savebtn);
        textView=findViewById(R.id.textwelcome);


        preferences = this.getSharedPreferences("com.example.finalprojectgroup8", Context.MODE_PRIVATE);
        userfromsession = preferences.getString("username", null);

        textView.setText("Welcome"+" "+userfromsession);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Conditionsforfields();
                if (flagcheck == false);
                senddatatodatabase();

                Toast.makeText(ProfileCreationForNanny.this,"Profile Created Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileCreationForNanny.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedetailsforNanny();
                Toast.makeText(ProfileCreationForNanny.this, "Profile Details Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void senddatatodatabase(){

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Profile Creation For Nanny");
        Creationdetails = new JavaDetailsCreationClass();
        Creationdetails.setLocation(saveloc);
        Creationdetails.setRate(savehour);
        Creationdetails.setDescription(savedescrip);
        Creationdetails.setFullname(savefname);
        Creationdetails.setChildren(savechild);
        Creationdetails.setUsername(userfromsession);
        reference.child(userfromsession).setValue(Creationdetails);


    }


    public void Conditionsforfields(){

        flagcheck=false;
        savedescrip=editdescrip.getText().toString();
        saveloc=editlocation.getText().toString();
        savefname=editfullname.getText().toString();
        savehour=editperhour.getText().toString();
        savechild=editchildren.getText().toString();

        if (TextUtils.isEmpty(savedescrip)){
            editdescrip.setError("Empty Field");
            flagcheck=true;
        }
        if (TextUtils.isEmpty(saveloc)){
            editlocation.setError("Empty Field");
            flagcheck=true;
        }
        if (TextUtils.isEmpty(savefname)){
            editfullname.setError("Empty Field");
            flagcheck=true;
        }
        if (TextUtils.isEmpty(savehour)){
            editperhour.setError("Empty Field");
            flagcheck=true;
        }
        if (TextUtils.isEmpty(savechild)){
            editchildren.setError("Empty Field");
            flagcheck=true;
        }

    }

    public void savedetailsmethod(){
        Intent intent=getIntent();
        showdescp=intent.getStringExtra("sendfnannydescp");
        showloc=intent.getStringExtra("sendfnannylocation");
        showfname=intent.getStringExtra("sendfnannyname");
        showrate=intent.getStringExtra("sendfrate");
        showchild=intent.getStringExtra("sendchild");
        editperhour = findViewById(R.id.edithour);
        editperhour.setText(showrate);
        editfullname = findViewById(R.id.editfname);
        editfullname.setText(showfname);
        editlocation = findViewById(R.id.editloc);
        editlocation.setText(showloc);
        editdescrip = findViewById(R.id.editdescription);
        editdescrip.setText(showdescp);
        editchildren = findViewById(R.id.editkids);
        editchildren.setText(showchild);
    }


    public void updatedetailsforNanny(){
        //Conditionsforfields();
        savechild=editchildren.getText().toString();
        Log.i("Check","agelabel"+savechild);
        savedescrip=editdescrip.getText().toString();
        saveloc=editlocation.getText().toString();
        savefname=editfullname.getText().toString();
        savehour=editperhour.getText().toString();

        reference=FirebaseDatabase.getInstance().getReference("Profile Creation For Nanny");
        Query checkupdateuser=reference.orderByChild("username").equalTo(userfromsession);
        checkupdateuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String childfromdb= dataSnapshot.child(userfromsession).child("children").getValue(String.class);
                    String desfromdb= dataSnapshot.child(userfromsession).child("description").getValue(String.class);
                    String locfromdb= dataSnapshot.child(userfromsession).child("location").getValue(String.class);
                    String ratefromdb= dataSnapshot.child(userfromsession).child("rate").getValue(String.class);
                    String fnamefromdb= dataSnapshot.child(userfromsession).child("fullname").getValue(String.class);

                    if (!childfromdb.equals(savechild)){
                        reference.child(userfromsession).child("age").setValue(savechild);
                    }
                    if (!desfromdb.equals(savedescrip)){
                        reference.child(userfromsession).child("description").setValue(savedescrip);
                    }
                    if (!locfromdb.equals(saveloc)){
                        reference.child(userfromsession).child("location").setValue(saveloc);
                    }
                    if (!ratefromdb.equals(savehour)){
                        reference.child(userfromsession).child("rate").setValue(savehour);
                    }
                    if (!fnamefromdb.equals(savefname)){
                        reference.child(userfromsession).child("fullname").setValue(savefname);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





}
