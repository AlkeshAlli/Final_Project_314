package com.example.finalprojectgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class DetailsForNanny extends AppCompatActivity {
    ImageView profilepicture;
    TextView profilename,profilelocation,profiledescription,profilewage,profilechildren;
    Button rate;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_for_nanny);
        int pic = R.drawable.logo;
        profilepicture = findViewById(R.id.forprofilepic);
        profilename = findViewById(R.id.forprofilename);
        profilelocation = findViewById(R.id.forprofilelocation);
        profilechildren = findViewById(R.id.forprofilechildren);
        profiledescription = findViewById(R.id.forprofiledescription);
        profilewage = findViewById(R.id.forprofilewage);
        rate = findViewById(R.id.forrate);
        check();
        setData();
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DetailsForNanny.this,Rating.class);
                i.putExtra("username",name);
                i.putExtra("status","For Nanny");
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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Profile Creation For Nanny");
        Query checkuser = reference.orderByChild("username").equalTo(name);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Log.d("name test",name);
                    String dbchildren= (String) dataSnapshot.child(name).child("children").getValue(String.class);
                    String dbwage= (String) dataSnapshot.child(name).child("rate").getValue(String.class);
                    String dblocation=dataSnapshot.child(name).child("location").getValue(String.class);
                    String dbdescription=dataSnapshot.child(name).child("description").getValue(String.class);
                    int pic = R.drawable.logo;
                    profilepicture.setImageResource(pic);
                    profilename.setText(name);
                    profilelocation.setText("Location: "+ dblocation);
                    profilechildren.setText("No of Children: "+dbchildren.toString());
                    profiledescription.setText("Description: \n"+dbdescription);
                    profilewage.setText("Expected Wage: $"+dbwage.toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}