package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileCreationAsNanny extends AppCompatActivity {

    EditText editage,editdescrip,editlocation,editfullname,editexperience,editperhour;
    TextView textView;

    String saveage,savedescrip,saveloc,savefname,saveexp,savehour;
    Button save;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SharedPreferences preferences;
    String userfromsession;
    JavaDetailsCreationClass Creationdetails;
    Boolean flagcheck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation_as_nanny);

        editage = findViewById(R.id.editage);
        editexperience = findViewById(R.id.editexp);
        editperhour = findViewById(R.id.edithour);
        editfullname = findViewById(R.id.editfname);
        editlocation = findViewById(R.id.editloc);
        editdescrip = findViewById(R.id.editdescription);
        save = findViewById(R.id.savebtn);
        textView=findViewById(R.id.textwelcome);


        preferences = this.getSharedPreferences("com.example.finalprojectgroup8", Context.MODE_PRIVATE);
        userfromsession = preferences.getString("username", null);
        Log.i("check","welcome"+userfromsession);

        textView.setText("Welcome"+" "+userfromsession);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conditionsforfields();
                if (flagcheck == false);
                senddatatodatabase();
                Toast.makeText(ProfileCreationAsNanny.this,"Profile Created Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void senddatatodatabase(){

                Log.i("Check", "ENTRY LOOP");

                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Profile Creation AsNanny");
                Creationdetails = new JavaDetailsCreationClass();
                Creationdetails.setLocation(saveloc);
                Creationdetails.setRate(savehour);
                Creationdetails.setAge(saveage);
                Creationdetails.setExperience(saveexp);
                Creationdetails.setDescription(savedescrip);
                Creationdetails.setFullname(savefname);
                reference.child(userfromsession).setValue(Creationdetails);
    }


    private void Conditionsforfields(){
        flagcheck=false;
        saveage=editage.getText().toString();
        Log.i("Check","agelabel"+saveage);
        savedescrip=editdescrip.getText().toString();
        saveloc=editlocation.getText().toString();
        savefname=editfullname.getText().toString();
        saveexp=editexperience.getText().toString();
        savehour=editperhour.getText().toString();

        if (TextUtils.isEmpty(saveage)){
            editage.setError("Empty Field");
            flagcheck=true;
        }
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
        if (TextUtils.isEmpty(saveexp)){
            editexperience.setError("Empty Field");
            flagcheck=true;
        }
        if (TextUtils.isEmpty(savehour)){
            editperhour.setError("Empty Field");
            flagcheck=true;
        }

    }

}
