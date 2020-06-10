package com.example.finalprojectgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    ImageView profilepicture;
    TextView profilename,profileemail;
    Button rate;

    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        profilepicture = findViewById(R.id.profilepic);
        profilename = findViewById(R.id.profilename);
        profileemail = findViewById(R.id.profileemail);
        rate = findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DetailsActivity.this,Rating.class);
                startActivity(i);
            }
        });

        getData();
        setData();


    }
    private void getData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("email")){
            name=getIntent().getStringExtra("name");
            email=getIntent().getStringExtra("email");
        }
        else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();

        }
    }
    private void setData(){
        profilename.setText(name);
        profileemail.setText(email);
    }
}